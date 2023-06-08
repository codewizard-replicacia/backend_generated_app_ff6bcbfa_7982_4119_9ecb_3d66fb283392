package com.mycompany.group234.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.logging.SessionLog;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class EclipseLinkJpaConfiguration extends JpaBaseConfiguration {
  @Value("${odata.jpa.punit_name}")
  private String punit;
  @Value("${odata.jpa.root_packages}")
  private String rootPackage;
  @Value("${eclipselink.ddl.auto.create.delete}")
  private boolean ddlAuto;
  
  protected EclipseLinkJpaConfiguration(DataSource dataSource, JpaProperties properties,
      ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
    super(dataSource, properties, jtaTransactionManager);
  }

  @Override
  protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
    return new EclipseLinkJpaVendorAdapter();
  }

  @Override
  protected Map<String, Object> getVendorProperties() {
    
    HashMap<String, Object> jpaProperties = new HashMap<>();
    jpaProperties.put(PersistenceUnitProperties.WEAVING, "false");
    jpaProperties.put(PersistenceUnitProperties.DDL_GENERATION, "none");
    jpaProperties.put(PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.FINE_LABEL);
    jpaProperties.put(PersistenceUnitProperties.TRANSACTION_TYPE, "RESOURCE_LOCAL");
    
    jpaProperties.put(PersistenceUnitProperties.CACHE_SHARED_DEFAULT, "false");
    
    
    jpaProperties.put(PersistenceUnitProperties.CONNECTION_POOL_MAX, 50);
    jpaProperties.put(PersistenceUnitProperties.LOGGING_LEVEL, "FINE");
    jpaProperties.put("eclipselink.logging.level.sql", "FINE");
    jpaProperties.put("eclipselink.logging.parameters", "true");
    if(ddlAuto)
      jpaProperties.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.DROP_AND_CREATE);
    return jpaProperties;
  }
  
  @Bean("entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(
      final EntityManagerFactoryBuilder builder, @Autowired final DataSource ds) {

    return builder
        .dataSource(ds)
            .packages(rootPackage)
        .properties(getVendorProperties())
        .jta(false)
        .build();
  }
}