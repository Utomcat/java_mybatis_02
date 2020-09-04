package com.ranyk.mybatis.concurrent.test.demo02;

/**
 * ClassName:MyThreadTest
 * Description:多线程synchronized关键字解析例一
 *
 * @author ranyi
 * @date 2020-06-06 17:26
 * Version: V1.0
 */
public class MyThreadTest {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();

        Thread thread1 = new Thread(myThread);
        Thread thread2 = new Thread(myThread);

        thread1.start();
        thread2.start();

    }

}

class MyThread implements Runnable {

    int x;

    @Override
    public void run() {
        x = 0;
        while (true){
            System.out.println("当前x的值为："+ (x++));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (x == 30){
                break;
            }
        }
    }
}
