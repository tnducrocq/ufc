package com.fcannizzaro.jsoup.annotations.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Francesco Cannizzaro (fcannizzaro)
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Html {
    String value();

    int index() default -1;

    boolean optional() default false;
}
