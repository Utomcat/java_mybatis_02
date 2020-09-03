package com.ranyk.mybatis_ch02.concurrent.test.demo03;

/**
 * ClassName:MyTest4
 * Description:synchronized关键字学习四
 *
 * @author ranyi
 * @date 2020-06-09 0:07
 * Version: V1.0
 */
public class MyTest4 {

    //private Object obj = new Object();

    public void method() {
        Object obj = new Object();
        synchronized (obj) {
            System.out.println("hello world!");
        }

    }


}
