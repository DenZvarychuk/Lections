package com.javarush.lections.lection02018.example1;

import java.lang.annotation.*;

/**
 * to be described in java.doc
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Security{
    Role role() default Role.USER;
    // if field called value() it possible to not write in annotation declaration
}
