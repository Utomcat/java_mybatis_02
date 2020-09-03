package com.ranyk.mybatis_ch02.thread_test;

/**
 * ClassName:ThreadDemo01
 * Description:线程的创建类一  使用继承Thread的方式
 *
 * @author ranyi
 * @date 2020-05-30 13:45
 * Version: V1.0
 */
public class ThreadDemo01 extends Thread {

    private Thread thread;
    private String threadName;

    ThreadDemo01(String name) {
        this.threadName = name;
        System.out.println("Creating：" + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);

        try {
            for (int i = 0; i < 4; i++) {
                System.out.println("Thread：" + threadName + ",当前i的值为：" + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + "interrupted.");
        }
        System.out.println("Thread " + threadName + "exiting.");
    }

    @Override
    public void start() {
        System.out.println("Starting " + threadName + "启动线程.");
        if (thread == null){
            thread = new Thread(this,threadName);
        }
        thread.start();
    }


}
