package com.ranyk.mybatis.concurrent.test.demo08;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * ClassName:MyTest1
 * Description:AQS学习一
 *
 * @author ranyi
 * @date 2020-06-17 16:38
 * Version: V1.0
 */
public class MyTest1 {

    private Lock lock = new ReentrantLock();



    public void method(){

        lock.lock();
        try {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("method invoke");

        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyTest1 myTest1 = new MyTest1();

        IntStream.range(0,10).forEach(i -> {
            new Thread(myTest1::method).start();
        });

    }

}
