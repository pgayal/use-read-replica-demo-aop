# Use Read Replica Demo for Select Queries using AOP
This project shows show how to use read-replica to run select queries using Spring Context and AOP

Run the [database-initialize-script.sql](/src/main/resources/database-initialize-script.sql) file to create test databases on local mysql
and update the credentials in the [application.properties](/src/main/resources/application.properties) accordingly.

## How does it work?

We have to configure 2 data sources where primary data source connects to writer and other one
connects to the read-replica. Separate set of connection parameters are defined in the `application.properties`

```
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
```

This application uses the [RoutingDataSource.java](/src/main/java/com/example/usereadreplicademoaop/config/RoutingDataSource.java) which extends spring's [AbstractRoutingDataSource.java](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/datasource/lookup/AbstractRoutingDataSource.html)
class to route the queries to a specific datasource based on the context. The context for each method call is set by AOP [ReadOnlyRouteInterceptor.java](/src/main/java/com/example/usereadreplicademoaop/aop/ReadOnlyRouteInterceptor.java)

To make any method query the read-replica, you just have to use the `@ReadOnlyQuery` annotation on the service method which want to query on the read-replica.

**NO OTHER CHANGE IS NEEDED ON THE DAILY TASK OF WRITING NEW METHODS**

<br/>

### Implementation Steps to Move Heavy Load Select Queries to Read-Replica

1. While writing new methods or updating existing ones,
    - Annotate the  `@ReadOnlyQuery` - VERY IMPORTANT
2. DONE

<br/>

### Classes to Refer
1. [application.properties](/src/main/resources/application.properties)
2. [PrimaryDataSourceConfiguration.java](/src/main/java/com/example/usereadreplicademoaop/config/PrimaryDataSourceConfiguration.java)
5. [EngagementService.java](/src/main/java/com/example/usereadreplicademoaop/service/EngagementService.java) - refer `List<EngagementDTO> getEngagements(){ ... }` method


