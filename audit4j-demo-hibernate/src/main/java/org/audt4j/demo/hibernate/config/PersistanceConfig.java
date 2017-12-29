package org.audt4j.demo.hibernate.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistanceConfig {

    private static final String PUName = "demo";

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL).build();
        return db;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPersistenceUnitName(PersistanceConfig.PUName);
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactory.setPackagesToScan("org.audt4j.demo.hibernate.model");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        Properties additionalProperties = new Properties();
        additionalProperties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        additionalProperties.put("hibernate.show_sql", true);
        additionalProperties.put("hibernate.hbm2ddl.auto", "update");
        entityManagerFactory.setJpaProperties(additionalProperties);
        return entityManagerFactory;
    }

    /**
     * Transaction manager.
     *
     * @return the platform transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(this.entityManagerFactory().getObject());
        return tm;
    }

    /**
     * Exception translation.
     *
     * @return the persistence exception translation post processor
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}