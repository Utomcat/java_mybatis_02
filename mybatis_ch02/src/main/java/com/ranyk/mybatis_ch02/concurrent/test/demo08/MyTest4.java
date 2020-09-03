package com.ranyk.mybatis_ch02.concurrent.test.demo08;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ClassName:MyTest4
 * Description:ReentrantReadWriteLock之 一个线程拥有读锁时能否去获取写锁
 *
 * @author ranyi
 * @date 2020-06-23 1:56
 * Version: V1.0
 */
public class MyTest4 {

    ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    private boolean update = false;

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock =  readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {

        System.out.println("当前线程名称为：" + Thread.currentThread().getName());

        MyTest4 myTest4 = new MyTest4();

        if (!myTest4.update){

            System.out.println("上读锁之前");
            myTest4.readLock.lock();
            try{
                System.out.println("上读锁之后，上写锁之前");
                System.out.println("上写锁之前读 stringThreadLocal = " + myTest4.stringThreadLocal.get());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //myTest4.readLock.unlock(); //在拥有读锁的情况下如果需要获取写锁则需要先将读锁释放掉才能获取对应的读锁
                myTest4.writeLock.lock();
                try {

                    System.out.println("上写锁之后读 stringThreadLocal = " + myTest4.stringThreadLocal.get());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    System.out.println("上读锁之后，上写锁之后");
                    myTest4.stringThreadLocal.set(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //myTest4.readLock.lock(); //在写锁释放之前或之后重新获取对应的读锁
                    System.out.println("上写锁之后，释放写锁之前读 stringThreadLocal = " + myTest4.stringThreadLocal.get());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }finally {
                    myTest4.writeLock.unlock();
                    System.out.println("上读锁之后，释放读锁之前，释放写锁");
                }

                System.out.println("释放写锁之后读 stringThreadLocal = " + myTest4.stringThreadLocal.get());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }finally {
                myTest4.readLock.unlock();
                System.out.println("释放读锁");
            }

        }

    }

}
