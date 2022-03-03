package com.example.usereadreplicademoaop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * This is data source configuration which defines 2 data source for writer and read-replica then use RoutingDataSource
 * to route the queries appropriately
 *
 * @author pgayal
 * created on 03/03/2022
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.example.usereadreplicademoaop", entityManagerFactoryRef = "entityManagerFactory")
public class PrimaryDataSourceConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(PrimaryDataSourceConfiguration.class);


    private final Environment environment;

    @Autowired
    public PrimaryDataSourceConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        final RoutingDataSource routingDataSource = new RoutingDataSource();

        logger.info("Configure Primary datasource: started");
        final DataSource primaryDataSource = createDataSource("spring.datasource");
        logger.info("Configure Primary datasource: completed");

        logger.info("Configure ReadReplica datasource: started");
        final DataSource replicaDataSource = createDataSource("spring.readonly.datasource");
        logger.info("Configure ReadReplica datasource: completed");

        final Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(RoutingDataSource.Route.PRIMARY, primaryDataSource);
        targetDataSources.put(RoutingDataSource.Route.REPLICA, replicaDataSource);
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(primaryDataSource);
        return routingDataSource;
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.example.usereadreplicademoaop.model")
                .properties(jpaProperties())
                .persistenceUnit("dataSource").build();
    }

    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        return props;
    }

    private DataSource createDataSource(String propertyPrefix) {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(environment.getProperty(String.format("%s.driver-class-name", propertyPrefix)));
        dataSourceBuilder.url(environment.getProperty(String.format("%s.jdbc-url", propertyPrefix)));
        dataSourceBuilder.username(environment.getProperty(String.format("%s.username", propertyPrefix)));
        dataSourceBuilder.password(environment.getProperty(String.format("%s.password", propertyPrefix)));
        return dataSourceBuilder.build();
    }

}
