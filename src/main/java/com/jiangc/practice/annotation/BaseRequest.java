package com.jiangc.practice.annotation;

import com.sun.istack.internal.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ：jiangcheng
 * @version : 1.0.0
 * @description ：TODO
 * @date ： 2022/6/11 09:57
 */
public class BaseRequest {
    public void nullFieldValidate() throws IllegalAccessException, InvocationTargetException {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Object fieldValue = runGetter(field, this);

            Annotation[] declaredAnnotations = field.getAnnotations();
            for (Annotation annotation : declaredAnnotations) {
                System.out.println(field.getName() + " ========");
                if (annotation instanceof MyNotNullAnnotation && fieldValue == null) {
                    System.out.println(field.getName() + " can't be null");
                }
            }
            boolean isAnnotationNotNull = field.isAnnotationPresent(NotNull.class);
            if (isAnnotationNotNull && fieldValue == null) {
                System.out.println(fieldName + " can't be null");
            }
        }
    }

    // 由于所有子类的属性都是private的，所以必须要找到属性的getter方法
    //  以下代码借鉴[stackoverflow的文章](https://stackoverflow.com/questions/13400075/reflection-generic-get-field-value)
    public Object runGetter(Field field, Object instance) {
        // MZ: Find the correct method
        for (Method method : instance.getClass().getDeclaredMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                    // MZ: Method found, run it
                    try {
                        return method.invoke(instance);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.println("Could not determine method: " + method.getName());
                    }
                }
            }
        }
        return null;
    }
}