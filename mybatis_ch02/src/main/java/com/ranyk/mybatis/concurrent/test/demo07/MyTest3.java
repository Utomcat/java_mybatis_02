package com.ranyk.mybatis.concurrent.test.demo07;

/**
 * ClassName:MyTest3
 * Description:ThreadLocal学习一
 *
 * @author ranyi
 * @date 2020-06-14 0:01
 * Version: V1.0
 */
public class MyTest3 {

    public static void main(String[] args) {

        ThreadLocal<String> threadLocal = new ThreadLocal<String>();

        threadLocal.set("hello world");

        System.out.println(threadLocal.get());

        threadLocal.set("welcome");

        System.out.println(threadLocal.get());


    }

}
