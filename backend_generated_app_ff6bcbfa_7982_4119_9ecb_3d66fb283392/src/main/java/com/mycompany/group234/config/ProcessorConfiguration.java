package com.mycompany.group234.config;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import javax.persistence.EntityManagerFactory;

import com.sap.olingo.jpa.processor.core.database.JPA_POSTSQL_DatabaseProcessor;
import com.mycompany.group234.nameBuilder.APINameBuilder;
import org.apache.olingo.commons.api.ex.ODataException;
import org.apache.olingo.server.api.debug.DefaultDebugSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.sap.olingo.jpa.processor.core.api.JPAODataRequestContext;
import com.sap.olingo.jpa.processor.core.api.JPAODataServiceContext;
import com.sap.olingo.jpa.processor.core.api.JPAODataSessionContextAccess;
import com.sap.olingo.jpa.processor.core.api.example.JPAExampleCUDRequestHandler;

@Configuration
public class ProcessorConfiguration {
  @Value("${odata.jpa.punit_name}")
  private String punit;
  @Value("${odata.jpa.root_packages}") 
  private String rootPackages;
  
  @Bean
  public JPAODataSessionContextAccess sessionContext(@Autowired final EntityManagerFactory emf) throws ODataException {

    return JPAODataServiceContext.with()
        .setPUnit(punit)
        .setEntityManagerFactory(emf)
        .setTypePackage(rootPackages)
            .setEdmNameBuilder(new APINameBuilder(punit))
            .setDatabaseProcessor(new JPA_POSTSQL_DatabaseProcessor())
        .setRequestMappingPath("generated_app")
        .build();
  }
  
  @Bean
  @Scope(scopeName = SCOPE_REQUEST)
  public JPAODataRequestContext requestContext() {

    return JPAODataRequestContext.with()
        .setCUDRequestHandler(new JPAExampleCUDRequestHandler())
        .setDebugSupport(new DefaultDebugSupport())
        .build();
  }
}