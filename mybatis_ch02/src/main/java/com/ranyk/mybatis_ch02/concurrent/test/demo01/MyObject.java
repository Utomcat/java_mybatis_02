package com.ranyk.mybatis_ch02.concurrent.test.demo01;

/**
 * ClassName:MyObject
 * Description:多线程练习一
 *
 * 1. 存在一个对象，该对象有一个int类型的成员变量counter，该成员变量的初始值为0
 * 2. 创建两个线程，其中一个线程对该对象的成员变量counter增1，另一个线程对该对象的成员变量减1
 * 3. 输出该对象成员变量counter每次变化后的值
 * 4. 最终输出结果应为：10101010101...
 *
 * @author ranyi
 * @date 2020-06-05 18:56
 * Version: V1.0
 */
public class MyObject {

    private int counter = 0;

    /**
     * 当counter的值不为0的时候，不做任何操作，线程进入等待
     */
    public synchronized void increase(){

        if (counter == 1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        counter++;
        System.out.println("counter的值为："+counter);
        notify();


    }

    /**
     * 当counter的值不为1的时候，不做任何操作，线程等待
     */
    public synchronized void decrease(){

        if (counter == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        counter--;
        System.out.println("counter的值为："+counter);
        notify();

    }


    /**
     * 当counter的值不为0的时候，不做任何操作，线程进入等待
     */
    public synchronized void increase2(){

        while (counter == 1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        counter++;
        System.out.println("counter的值为："+counter);
        notify();


    }

    /**
     * 当counter的值不为1的时候，不做任何操作，线程等待
     */
    public synchronized void decrease2(){

        while (counter == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        counter--;
        System.out.println("counter的值为："+counter);
        notify();

    }


    /**
     * 当counter的值不为0的时候，不做任何操作，线程进入等待
     */
    public synchronized void increase3(){

        do {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (counter == 1);

        counter++;
        System.out.println("counter的值为："+counter);
        notify();


    }

    /**
     * 当counter的值不为1的时候，不做任何操作，线程等待
     */
    public synchronized void decrease3(){

        do {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (counter == 0);

        counter--;
        System.out.println("counter的值为："+counter);
        notify();

    }



    public synchronized void increase1(){

        if (counter != 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter++;
        System.out.println("counter的值为："+counter);
        notify();


    }

    public synchronized void decrease1(){

        if (counter != 1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter--;
        System.out.println("counter的值为："+counter);
        notify();

    }

}
