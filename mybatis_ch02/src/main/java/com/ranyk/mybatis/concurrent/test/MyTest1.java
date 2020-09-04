package com.ranyk.mybatis.concurrent.test;

/**
 * ClassName:MyTest1
 * Description:测试一
 *
 * @author ranyi
 * @date 2020-06-04 21:50
 * Version: V1.0
 */
public class MyTest1 {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        synchronized (o) {
            o.wait();
        }
    }
}
