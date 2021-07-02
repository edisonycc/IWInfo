package com.innowing.info.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "doorAccessSysEntityManagerFactory",
        transactionManagerRef = "doorAccessSysTransactionManager",
        basePackages = {"com.innowing.info.repository.doorAccessSys"})
public class DoorAccessSysDataSourceConfiguration {
    @Bean(name = "doorAccessSysDataSourceProperties")
    @ConfigurationProperties("spring.datasource-door-access-sys")
    public DataSourceProperties doorAccessSysDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "doorAccessSysDataSource")
    @ConfigurationProperties("spring.datasource-door-access-sys.configuration")
    public DataSource doorAccessSysDataSource(@Qualifier("doorAccessSysDataSourceProperties") DataSourceProperties doorAccessSysDataSourceProperties) {
        return doorAccessSysDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "doorAccessSysEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean doorAccessSysEntityManagerFactory(
            EntityManagerFactoryBuilder doorAccessSysEntityManagerFactoryBuilder, @Qualifier("doorAccessSysDataSource") DataSource doorAccessSysDataSource) {

        Map<String, String> doorAccessSysJpaProperties = new HashMap<>();
        doorAccessSysJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        doorAccessSysJpaProperties.put("hibernate.hbm2ddl.auto", "none");

        return doorAccessSysEntityManagerFactoryBuilder
                .dataSource(doorAccessSysDataSource)
                .packages("com.innowing.info.entity.doorAccessSys")
                .persistenceUnit("doorAccessSysDataSource")
                .properties(doorAccessSysJpaProperties)
                .build();
    }

    @Bean(name = "doorAccessSysTransactionManager")
    public PlatformTransactionManager doorAccessSysTransactionManager(
            @Qualifier("doorAccessSysEntityManagerFactory") EntityManagerFactory doorAccessSysEntityManagerFactory) {

        return new JpaTransactionManager(doorAccessSysEntityManagerFactory);
    }
}
