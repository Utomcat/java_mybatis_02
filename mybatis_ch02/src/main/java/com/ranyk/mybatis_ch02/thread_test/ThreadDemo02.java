package com.ranyk.mybatis_ch02.thread_test;

/**
 * ClassName:ThreadDemo02
 * Description:线程的创建类二  使用实现Runnable接口的方式创建
 *
 * @author ranyi
 * @date 2020-06-02 15:27
 * Version: V1.0
 */
public class ThreadDemo02 implements Runnable{

    private Thread thread;
    private String threadName;

    public ThreadDemo02(String threadName) {
        this.threadName = threadName;
        System.out.println("Creating：" + threadName);
    }

    /**
     * 重写Runnable接口的run方法
     * 实现从0——3的四次输出
     */
    @Override
    public void run(){

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


    public void start() {
        System.out.println("Starting " + threadName + "启动线程.");
        if (thread == null){
            thread = new Thread(this,threadName);
        }
        thread.start();
    }


}
