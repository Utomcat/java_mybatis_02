package com.ranyk.mybatis_ch02.concurrent.test.demo02;

/**
 * ClassName:MyThreadTest2
 * Description:多线程-关键字synchronized解析测试二
 *
 * @author ranyi
 * @date 2020-06-06 17:30
 * Version: V1.0
 */
public class MyThreadTest2 {

    /**
     * 如果某个对象存在多个非静态的synchronized的方法时，
     * 那么在某一时刻有且仅有一个线程进入到该对象的synchronized方法，
     * 同一个类的不同对象，是互不影响的，即他们各自执行自己的，不存在锁的分配问题
     * 当一个类中存在静态的synchronized的方法时，其调用和同一个类的不同对象一样，互不影响，因为其对应的锁对象不同
     * 使用方式一的方式打印结果为：
     * hello
     * world
     * <p>
     * 使用方式二的方式打印结果为：
     * 先打印
     * world
     * 打印后等待几秒在打印
     * hello
     *
     * @param args
     */
    public static void main(String[] args) {

        /** ================== 方式一 =========================== **/
        MyClass myClass = new MyClass();

        Thread1 thread1 = new Thread1(myClass);
        Thread2 thread2 = new Thread2(myClass);
        Thread3 thread3 = new Thread3();


        thread1.start();
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
        thread3.start();


        /** ================== 方式二 =========================== **/
        /*MyClass myClass = new MyClass();
        MyClass myClass2 = new MyClass();

        Thread1 thread1 = new Thread1(myClass);
        Thread2 thread2 = new Thread2(myClass2);

        thread1.start();
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();*/

    }

}

class MyClass {
    public synchronized void hello() {
        System.out.println("hello 111111");

        try {
            System.out.println("hello 等待中。。。。");
            Thread.sleep(4000);
            System.out.println("hello 等待结束。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello 2222222");
    }

    public synchronized void word() {
        System.out.println("world");
    }

    public static synchronized void hello1() {
        System.out.println("hello1 1111111");

        try {
            System.out.println("hello1 等待中。。。。");
            Thread.sleep(4000);
            System.out.println("hello1 等待结束。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello1 22222222");
    }

    public static synchronized void hello2() {
        System.out.println("hello2");
    }

}

class Thread1 extends Thread {
    private MyClass myClass;

    Thread1(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public void run() {
        myClass.hello();
    }
}

class Thread2 extends Thread {
    private MyClass myClass;

    Thread2(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public void run() {
        myClass.word();
    }
}

class Thread3 extends Thread {



    @Override
    public void run() {
        MyClass.hello1();
    }
}