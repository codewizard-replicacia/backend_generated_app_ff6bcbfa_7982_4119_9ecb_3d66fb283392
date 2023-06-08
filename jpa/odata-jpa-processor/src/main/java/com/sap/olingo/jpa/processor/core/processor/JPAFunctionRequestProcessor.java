package com.sap.olingo.jpa.processor.core.processor;

import static com.sap.olingo.jpa.processor.core.exception.ODataJPAProcessorException.MessageKeys.ENUMERATION_UNKNOWN;
import static com.sap.olingo.jpa.processor.core.exception.ODataJPAProcessorException.MessageKeys.FUNCTION_UNKNOWN;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;

import org.apache.olingo.commons.api.data.Annotatable;
import org.apache.olingo.commons.api.edm.EdmFunction;
import org.apache.olingo.commons.api.edm.EdmParameter;
import org.apache.olingo.commons.api.edm.EdmPrimitiveType;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeException;
import org.apache.olingo.commons.api.edm.EdmType;
import org.apache.olingo.commons.api.ex.ODataException;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataLibraryException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.uri.UriParameter;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceFunction;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunctionType;
import com.sap.olingo.jpa.metadata.core.edm.mapper.api.JPADataBaseFunction;
import com.sap.olingo.jpa.metadata.core.edm.mapper.api.JPAEnumerationAttribute;
import com.sap.olingo.jpa.metadata.core.edm.mapper.api.JPAFunction;
import com.sap.olingo.jpa.metadata.core.edm.mapper.api.JPAJavaFunction;
import com.sap.olingo.jpa.metadata.core.edm.mapper.api.JPAParameter;
import com.sap.olingo.jpa.metadata.core.edm.mapper.exception.ODataJPAModelException;
import com.sap.olingo.jpa.processor.core.api.JPAODataDatabaseProcessor;
import com.sap.olingo.jpa.processor.core.api.JPAODataRequestContextAccess;
import com.sap.olingo.jpa.processor.core.exception.ODataJPADBAdaptorException;
import com.sap.olingo.jpa.processor.core.exception.ODataJPAProcessorException;
import org.apache.olingo.server.api.uri.queryoption.AliasQueryOption;
import org.apache.olingo.server.core.uri.UriInfoImpl;

/**
 * Functions as User Defined Functions, Native Query, as Criteria Builder does not provide the option to used UDFs in
 * the From clause.
 * @author Oliver Grande
 *
 */
public final class JPAFunctionRequestProcessor extends JPAOperationRequestProcessor implements JPARequestProcessor { // NOSONAR

  private final JPAODataDatabaseProcessor dbProcessor;

  public JPAFunctionRequestProcessor(final OData odata, final JPAODataRequestContextAccess requestContext)
      throws ODataException {
    super(odata, requestContext);
    this.dbProcessor = requestContext.getDatabaseProcessor();
  }

  @Override
  public void retrieveData(final ODataRequest request, final ODataResponse response, final ContentType responseFormat)
      throws ODataApplicationException, ODataLibraryException {

    final UriResourceFunction uriResourceFunction =
        (UriResourceFunction) uriInfo.getUriResourceParts().get(uriInfo.getUriResourceParts().size() - 1);
    AliasQueryOption aliasQueryOption = null;
    if(((UriInfoImpl) uriInfo).getAliases() != null && ((UriInfoImpl) uriInfo).getAliases().size() > 0)
     aliasQueryOption = ((UriInfoImpl) uriInfo).getAliases().get(((UriInfoImpl) uriInfo).getAliases().size()-1);
    final JPAFunction jpaFunction = sd.getFunction(uriResourceFunction.getFunction());
    if (jpaFunction == null)
      throw new ODataJPAProcessorException(FUNCTION_UNKNOWN, HttpStatusCode.BAD_REQUEST, uriResourceFunction
          .getFunction().getName());
    Object result = null;
    if (jpaFunction.getFunctionType() == EdmFunctionType.JavaClass) {
      result = processJavaFunction(uriResourceFunction, (JPAJavaFunction) jpaFunction, em, aliasQueryOption);

    } else if (jpaFunction.getFunctionType() == EdmFunctionType.UserDefinedFunction) {
      result = processJavaUDF(uriInfo.getUriResourceParts(), (JPADataBaseFunction) jpaFunction);
    }
    final EdmType returnType = uriResourceFunction.getFunction().getReturnType().getType();
    final Annotatable annotatable = convertResult(result, returnType, jpaFunction);
    serializeResult(returnType, response, responseFormat, annotatable, request);
  }

  private Object getValue(final EdmFunction edmFunction, final JPAParameter parameter, final String uriValue)
      throws ODataApplicationException {
    final String value = uriValue.replace("'", "");
    final EdmParameter edmParam = edmFunction.getParameter(parameter.getName());
    try {
      switch (edmParam.getType().getKind()) {
        case PRIMITIVE:
          return ((EdmPrimitiveType) edmParam.getType()).valueOfString(value, false, edmParam.getMaxLength(),
              edmParam.getPrecision(), edmParam.getScale(), true, parameter.getType());
        case ENUM:
          final JPAEnumerationAttribute enumeration = sd.getEnumType(parameter.getTypeFQN()
              .getFullQualifiedNameAsString());
          if (enumeration == null)
            throw new ODataJPAProcessorException(ENUMERATION_UNKNOWN, HttpStatusCode.BAD_REQUEST, parameter.getName());
          return enumeration.enumOf(value);
        default:
          throw new ODataJPADBAdaptorException(ODataJPADBAdaptorException.MessageKeys.PARAMETER_CONVERSION_ERROR,
              HttpStatusCode.NOT_IMPLEMENTED, uriValue, parameter.getName());
      }

    } catch (EdmPrimitiveTypeException | ODataJPAModelException e) {
      // Unable to convert value %1$s of parameter %2$s
      throw new ODataJPADBAdaptorException(ODataJPADBAdaptorException.MessageKeys.PARAMETER_CONVERSION_ERROR,
          HttpStatusCode.NOT_IMPLEMENTED, e, uriValue, parameter.getName());
    }
  }

  private Object processJavaFunction(final UriResourceFunction uriResourceFunction, final JPAJavaFunction jpaFunction,
                                     final EntityManager em, final AliasQueryOption aliasQueryOption) throws ODataApplicationException {

    try {
      final Object instance = createInstance(em, jpaFunction);
      final List<Object> parameter = fillParameter(uriResourceFunction, jpaFunction, aliasQueryOption);

      return jpaFunction.getMethod().invoke(instance, parameter.toArray());
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | ODataJPAModelException e) {
      throw new ODataJPAProcessorException(e, HttpStatusCode.INTERNAL_SERVER_ERROR);
    } catch (final InvocationTargetException e) {
      final Throwable cause = e.getCause();
      if (cause instanceof ODataApplicationException) {
        throw (ODataApplicationException) cause;
      } else {
        throw new ODataJPAProcessorException(e, HttpStatusCode.INTERNAL_SERVER_ERROR);
      }
    }
  }

  private Object createInstance(final EntityManager em, final JPAJavaFunction jpaFunction)
      throws InstantiationException, IllegalAccessException, InvocationTargetException {

    final Constructor<?> c = jpaFunction.getConstructor();
    if (c.getParameterCount() == 1)
      return c.newInstance(em);
    else
      return c.newInstance();
  }

  private List<Object> fillParameter(final UriResourceFunction uriResourceFunction, final JPAJavaFunction jpaFunction, final AliasQueryOption aliasQueryOption)
      throws ODataJPAModelException, ODataApplicationException {

    final Parameter[] methodParameter = jpaFunction.getMethod().getParameters();
    final List<Object> parameter = new ArrayList<>();
    for (final Parameter declaredParameter : methodParameter) {
      for (final UriParameter providedParameter : uriResourceFunction.getParameters()) {
        final JPAParameter jpaParameter = jpaFunction.getParameter(declaredParameter.getName());
        if (jpaParameter.getName().equals(providedParameter.getName())) {
          if(providedParameter.getText() == null && aliasQueryOption != null && aliasQueryOption.getName().equals(providedParameter.getAlias())) {
              String id = aliasQueryOption.getText();
              Object idField = null;
              ArrayList<Field> fieldsList = new ArrayList<>();
              Class type = declaredParameter.getType();
              while(!type.isAssignableFrom(Object.class)) {
                fieldsList.addAll(Arrays.asList(type.getDeclaredFields()));
                type = type.getSuperclass();
              }
              if(fieldsList != null) {
                for(Field field : fieldsList)
                  if(field.getAnnotation(Id.class) != null) {
                    if(field.getType().isAssignableFrom(Integer.class))
                        idField = new Integer(id);
                    else if(field.getType().isAssignableFrom(Long.class))
                        idField = new Long(id);
                    else if(field.getType().isAssignableFrom(String.class))
                       idField = id;
                    else if(field.getType().isAssignableFrom(Short.class))
                      idField = new Short(id);
                  }
              }
              if(idField == null)
                throw new ODataJPAModelException(ODataJPAModelException.MessageKeys.TYPE_NOT_SUPPORTED);
              Object instance = em.find(declaredParameter.getType(), idField);
              parameter.add(instance);
          } else
            parameter.add(getValue(uriResourceFunction.getFunction(), jpaParameter, providedParameter.getText()));
          break;
        }
      }
    }
    return parameter;
  }

  private Object processJavaUDF(final List<UriResource> uriResourceParts, final JPADataBaseFunction jpaFunction)
      throws ODataApplicationException {

    return dbProcessor.executeFunctionQuery(uriResourceParts, jpaFunction, em);
  }

}
