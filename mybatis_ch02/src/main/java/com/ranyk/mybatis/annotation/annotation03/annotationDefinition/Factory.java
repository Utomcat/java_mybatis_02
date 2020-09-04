package com.ranyk.mybatis.annotation.annotation03.annotationDefinition;

import java.lang.annotation.*;

/**
 * ClassName:Factory
 * Description:工厂注解定义：用来实现工厂模型的注解
 *
 * @author ranyi
 * @date 2020-08-06 19:19
 * Version: V1.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Factory {

    /**
     * 工厂的名称
     */
    Class type() default Class.class;

    /**
     * 用于确定应实例化哪个项目的标识符
     */
    String id() default "";

}
