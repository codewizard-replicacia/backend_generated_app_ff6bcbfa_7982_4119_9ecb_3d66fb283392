package com.mycompany.group234.nameBuilder;


import com.sap.olingo.jpa.metadata.core.edm.mapper.api.JPAEdmNameBuilder;
import com.sap.olingo.jpa.metadata.core.edm.mapper.impl.JPADefaultEdmNameBuilder;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.EntityType;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Date;

public class APINameBuilder implements JPAEdmNameBuilder {
    private final JPAEdmNameBuilder defaultNameBuilder;

    public APINameBuilder(final String punit) {
        defaultNameBuilder = new JPADefaultEdmNameBuilder(punit);
    }

    @Override
    public String buildComplexTypeName(final EmbeddableType<?> jpaEmbeddedType) {
        return defaultNameBuilder.buildComplexTypeName(jpaEmbeddedType);
    }

    @Override
    public String buildContainerName() {
        return defaultNameBuilder.buildContainerName();
    }

    @Override
	public String buildEntitySetName(final String entityTypeName) {
    	String eSetName = null;
    	
    	eSetName = "Trip".equals(entityTypeName) ? "Trips" : defaultNameBuilder.buildEntitySetName(entityTypeName);
    	eSetName = "Airline".equals(entityTypeName) ? "Airlines" : defaultNameBuilder.buildEntitySetName(entityTypeName);
    	eSetName = "Flight".equals(entityTypeName) ? "Flights" : defaultNameBuilder.buildEntitySetName(entityTypeName);
    	eSetName = "PlanItem".equals(entityTypeName) ? "PlanItems" : defaultNameBuilder.buildEntitySetName(entityTypeName);
    	eSetName = "Document".equals(entityTypeName) ? "Documents" : defaultNameBuilder.buildEntitySetName(entityTypeName);
    	eSetName = "Manager".equals(entityTypeName) ? "Managers" : defaultNameBuilder.buildEntitySetName(entityTypeName);
    	eSetName = "AbstractPerson".equals(entityTypeName) ? "AbstractPersons" : defaultNameBuilder.buildEntitySetName(entityTypeName);
    	eSetName = "Employee".equals(entityTypeName) ? "Employees" : defaultNameBuilder.buildEntitySetName(entityTypeName);
    	eSetName = "AbstractPlanItem".equals(entityTypeName) ? "AbstractPlanItems" : defaultNameBuilder.buildEntitySetName(entityTypeName);
    	eSetName = "Airport".equals(entityTypeName) ? "Airports" : defaultNameBuilder.buildEntitySetName(entityTypeName);
    	eSetName = "Event".equals(entityTypeName) ? "Events" : defaultNameBuilder.buildEntitySetName(entityTypeName);
    	eSetName = "PublicTransportation".equals(entityTypeName) ? "PublicTransportations" : defaultNameBuilder.buildEntitySetName(entityTypeName);
    	eSetName = "Person".equals(entityTypeName) ? "People" : defaultNameBuilder.buildEntitySetName(entityTypeName);
        return eSetName;
    }

    @Override
    public String buildEntityTypeName(final EntityType<?> jpaEntityType) {
        return defaultNameBuilder.buildEntityTypeName(jpaEntityType);
    }

    @Override
    public String buildEnumerationTypeName(final Class<? extends Enum<?>> javaEnum) {
        return defaultNameBuilder.buildEnumerationTypeName(javaEnum);
    }

    @Override
    public String buildNaviPropertyName(final Attribute<?, ?> jpaAttribute) {
        return defaultNameBuilder.buildNaviPropertyName(jpaAttribute);
    }

    @Override
    public String buildOperationName(final String internalOperationName) {
        return defaultNameBuilder.buildOperationName(internalOperationName);
    }

    @Override
    public String buildPropertyName(final String jpaAttributeName) {
        return defaultNameBuilder.buildPropertyName(jpaAttributeName);
    }

    @Override
    public String getNamespace() {
        return defaultNameBuilder.getNamespace();
    }
}
