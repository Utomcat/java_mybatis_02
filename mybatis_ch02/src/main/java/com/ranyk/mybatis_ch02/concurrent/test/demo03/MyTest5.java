package com.ranyk.mybatis_ch02.concurrent.test.demo03;

/**
 * ClassName:MyTest5
 * Description:synchronized关键字学习五
 *
 * @author ranyi
 * @date 2020-06-09 10:29
 * Version: V1.0
 */
public class MyTest5 {

    private Object obj = new Object();

    public void method() {
        synchronized (obj) {
            System.out.println("hello world");
        }

        synchronized (obj) {
            System.out.println("welcome");
        }

        synchronized (obj) {
            System.out.println("person");
        }
    }

}
