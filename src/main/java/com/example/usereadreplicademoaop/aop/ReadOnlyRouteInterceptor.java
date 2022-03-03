package com.example.usereadreplicademoaop.aop;

import com.example.usereadreplicademoaop.annotation.ReadOnlyQuery;
import com.example.usereadreplicademoaop.config.RoutingDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * This is the router to route ready only method queries to read-replica. It has a pointcut on all the methods having
 * a @ReadOnlyQuery annotation. For all of such method it changes the datasource context to route queries to read-replica
 *
 * @author pgayal
 * created on 03/03/2022
 */
@Aspect
@Component
@Order(0)
public class ReadOnlyRouteInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ReadOnlyRouteInterceptor.class);

    @Around("@annotation(readOnlyQuery)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, ReadOnlyQuery readOnlyQuery) throws Throwable {
        try {
            RoutingDataSource.setReplicaRoute();
            logger.info("Routing database call to the read replica: method=> " + proceedingJoinPoint.getSignature().toShortString()
                    + "| args => " + Arrays.asList(proceedingJoinPoint.getArgs()));
            return proceedingJoinPoint.proceed();
        } finally {
            RoutingDataSource.clearReplicaRoute();
        }
    }
}
