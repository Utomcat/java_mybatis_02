package com.ranyk.mybatis.concurrent.test.demo04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName:MyTest1
 * Description:Lock学习一
 *
 * @author ranyi
 * @date 2020-06-10 22:16
 * Version: V1.0
 */
public class MyTest1 {

    private Lock lock = new ReentrantLock();


    public void method1() {
        lock.lock();
        try {
            System.out.println("myMethod1 invoked");
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        lock.lock();
        try {
            System.out.println("myMethod2 invoked");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyTest1 myTest1 = new MyTest1();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                myTest1.method1();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"MyThread1");


        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                myTest1.method2();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"MyThread1");

        thread1.start();
        thread2.start();

    }


}
