package com.ranyk.mybatis.concurrent.test.demo06;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName:MyTest2
 * Description:CAS学习二
 *
 * @author ranyi
 * @date 2020-06-12 23:56
 * Version: V1.0
 */
public class MyTest2 {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println("获取atomicInteger的原始值为：" + atomicInteger.get());

        System.out.println("将atomicInteger的原始值替换为指定值并返回的原始值为：" + atomicInteger.getAndSet(8));

        System.out.println("获取atomicInteger替换后的值为：" + atomicInteger.get());

        System.out.println("将atomicInteger的值自增一并返回的原始值为：" + atomicInteger.getAndIncrement());

        System.out.println("获取atomicInteger自增后的值为：" + atomicInteger.get());

    }

}
