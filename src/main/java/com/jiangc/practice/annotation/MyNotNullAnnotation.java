package com.jiangc.practice.annotation;

import java.lang.annotation.*;

/**
 * @author ：jiangcheng
 * @version : 1.0.0
 * @description ：TODO
 * @date ： 2022/6/12 14:44
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyNotNullAnnotation {
}