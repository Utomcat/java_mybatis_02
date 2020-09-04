package com.ranyk.mybatis.concurrent.test.demo08;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * ClassName:MyTest2
 * Description:ReentrantReadWriteLock学习一
 *
 * @author ranyi
 * @date 2020-06-22 10:42
 * Version: V1.0
 */
public class MyTest2 {

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void method() {
        //readWriteLock.readLock().lock();
        readWriteLock.writeLock().lock();
        try {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("method invoke");

        } finally {
            //readWriteLock.readLock().unlock();
            readWriteLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        MyTest2 myTest2 = new MyTest2();

        IntStream.range(0, 10).forEach(i -> (new Thread(myTest2::method)).start());
    }
}
