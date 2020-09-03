package com.ranyk.mybatis_ch02.concurrent.test.demo09;

import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * ClassName:MyTest1
 * Description:ThreadPool学习一
 *
 * @author ranyi
 * @date 2020-06-23 12:01
 * Version: V1.0
 */
public class MyTest1 {

    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();

        IntStream.range(0,3).forEach(i ->{
            new Thread(() -> {
                reentrantLock.lock();
                try {
                    System.out.println("写线程");
                }finally {
                    //reentrantLock.unlock();
                }

            }).start();
        });

    }


}
