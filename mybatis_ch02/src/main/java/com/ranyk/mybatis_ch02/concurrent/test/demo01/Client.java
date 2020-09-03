package com.ranyk.mybatis_ch02.concurrent.test.demo01;

/**
 * ClassName:Client
 * Description:测试类
 *
 * @author ranyi
 * @date 2020-06-06 0:24
 * Version: V1.0
 */
public class Client {

    public static void main(String[] args) {

        MyObject myObject = new MyObject();

        Thread increaseThread = new IncreaseThread(myObject);
        Thread increaseThread1 = new IncreaseThread(myObject);
        Thread decreaseThread = new DecreaseThread(myObject);
        Thread decreaseThread1 = new DecreaseThread(myObject);

        increaseThread.start();
        increaseThread1.start();
        decreaseThread.start();
        decreaseThread1.start();

    }
}
