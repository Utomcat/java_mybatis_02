package com.ranyk.mybatis_ch02.concurrent.test.demo08;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ClassName:MyTest3
 * Description:ReentrantReadWriteLock之 一个线程拥有写锁时能否去获取读锁
 *
 * @author ranyi
 * @date 2020-06-23 1:38
 * Version: V1.0
 */
public class MyTest3 {

    ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    private boolean update = false;

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock =  readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {

        System.out.println("当前线程名称为：" + Thread.currentThread().getName());

        MyTest3 myTest3 = new MyTest3();

        if (!myTest3.update){

            System.out.println("上写锁之前");
            System.out.println("当前线程名称为：" + Thread.currentThread().getName());
            myTest3.writeLock.lock();
            try{
                System.out.println("上写锁之后，上读锁之前");
                System.out.println("当前线程名称为：" + Thread.currentThread().getName());
                myTest3.stringThreadLocal.set(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                myTest3.readLock.lock();
                System.out.println("当前线程名称为：" + Thread.currentThread().getName());
                try {
                    System.out.println("上写锁之后，上读锁之后");
                    System.out.println("stringThreadLocal = " + myTest3.stringThreadLocal.get());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }finally {
                    myTest3.readLock.unlock();
                    System.out.println("当前线程名称为：" + Thread.currentThread().getName());
                    System.out.println("上写锁之后，释放写锁之前，释放读锁");
                }

            }finally {
                myTest3.writeLock.unlock();
                System.out.println("当前线程名称为：" + Thread.currentThread().getName());
                System.out.println("释放写锁");
            }

        }

    }



}
