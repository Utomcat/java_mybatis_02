package com.ranyk.mybatis_ch02.concurrent.test.demo05;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * ClassName:MyTest1
 * Description:CountDownLatch学习一
 *
 * @author ranyi
 * @date 2020-06-12 10:15
 * Version: V1.0
 */
public class MyTest1 {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }).start());

        System.out.println("子线程启动完毕！");

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程执行完毕！");

    }

}
