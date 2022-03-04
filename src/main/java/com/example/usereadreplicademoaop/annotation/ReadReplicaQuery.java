package com.example.usereadreplicademoaop.annotation;

import java.lang.annotation.*;

/**
 * This is the annotation used on the methods which needs to connect to READONLY instance of the database.
 * It's then being used by ReadOnlyRouteInterceptor route the DB queries
 *
 * @author pgayal
 * created on 03/03/2022
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface ReadReplicaQuery {
}
