package com.ranyk.mybatis_ch02.annotation.annotation02;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName:NameScanner
 * Description: 定义编译时注解
 *
 * @author ranyi
 * @date 2020-08-06 15:51
 * Version: V1.0
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface NameScanner {
}
