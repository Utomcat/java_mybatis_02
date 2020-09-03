package com.ranyk.mybatis_ch02.concurrent.test.demo03;

/**
 * ClassName:MyTest6
 * Description:Lock的学习一
 *
 * @author ranyi
 * @date 2020-06-10 17:17
 * Version: V1.0
 */
public class MyTest6 {

    private Object lockObj1 = new Object();
    private Object lockObj2 = new Object();

    public void method1() {
        synchronized (lockObj1) {
            synchronized (lockObj2) {
                System.out.println("myMethod1 invoked");
            }
        }
    }

    public void method2() {
        synchronized (lockObj2) {
            synchronized (lockObj1) {
                System.out.println("myMethod2 invoked");
            }
        }
    }

    public static void main(String[] args) {
        MyTest6 myTest6 = new MyTest6();

        Runnable runnable1 = () -> {
            while (true) {
                myTest6.method1();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        };

        Thread thread1 = new Thread(runnable1, "Thread1");


        Runnable runnable2 = () -> {
            while (true) {
                myTest6.method2();

                try {
                    Thread.sleep(210);
                } catch (InterruptedException e) {
                }

            }
        };

        Thread thread2 = new Thread(runnable2, "Thread2");

        thread1.start();
        thread2.start();

    }


}
