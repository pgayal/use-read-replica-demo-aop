package com.example.usereadreplicademoaop.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * This is custom implementation of the AbstractRoutingDataSource to route the readonly queries appropriately
 * It is being used in the ReadOnlyRouteInterceptor class
 *
 * @author pgayal
 * created on 03/03/2022
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<Route> routeContext = new ThreadLocal<>();

    public enum Route {
        PRIMARY, REPLICA
    }

    public static void clearReplicaRoute() {
        routeContext.remove();
    }

    public static void setReplicaRoute() {
        routeContext.set(Route.REPLICA);
    }

    @Override
    public Object determineCurrentLookupKey() {
        return routeContext.get();
    }
}
