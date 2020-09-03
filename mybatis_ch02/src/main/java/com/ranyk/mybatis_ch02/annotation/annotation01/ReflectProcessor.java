package com.ranyk.mybatis_ch02.annotation.annotation01;

import java.lang.reflect.Method;

/**
 * ClassName:ReflectProcessor
 * Description:注解处理器
 *
 * @author ranyi
 * @date 2020-08-06 15:36
 * Version: V1.0
 */
public class ReflectProcessor {
    public void parseMethod(final  Class<?> clazz) throws Exception{

        final Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
        final Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            final Reflect annotation = declaredMethod.getAnnotation(Reflect.class);
            if (null != annotation){
                declaredMethod.invoke(obj,annotation.name());
            }
        }

    }
}
