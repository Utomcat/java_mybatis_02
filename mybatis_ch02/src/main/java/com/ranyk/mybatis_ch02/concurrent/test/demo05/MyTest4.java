package com.ranyk.mybatis_ch02.concurrent.test.demo05;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * ClassName:MyTest4
 * Description:CyclicBarrier学习三
 *
 * @author ranyi
 * @date 2020-06-12 22:24
 * Version: V1.0
 */
public class MyTest4 {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,() -> {
            System.out.println("hello world");
        });

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                new Thread(() -> {
                    try {
                        Thread.sleep((long)(Math.random() * 2000));

                        int randomInt = new Random().nextInt(500);

                        System.out.println("线程等待之前 hello "+ randomInt);

                        cyclicBarrier.await();

                        System.out.println("线程等待之后 hello "+ randomInt);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }).start();
            }
        }

    }

}
