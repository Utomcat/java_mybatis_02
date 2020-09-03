package com.ranyk.mybatis_ch02.annotation.annotation01;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName:Reflect
 * Description:定义运行时处理注解
 *
 * @author ranyi
 * @date 2020-08-06 15:33
 * Version: V1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Reflect {

    String name() default "aa";

}
