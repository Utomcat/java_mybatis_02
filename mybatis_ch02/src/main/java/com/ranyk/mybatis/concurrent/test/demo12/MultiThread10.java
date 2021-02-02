package com.ranyk.mybatis.concurrent.test.demo12;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * CLASS_NAME: MultiThread10<br/>
 * Description: 多线程示例 - 可重入读写锁练习使用<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Slf4j
public class MultiThread10 {

    /**
     * 定义需要进行操作的数据对象
     */
    private Map<String, String> map = new HashMap<String, String>();
    /**
     * 读写操作的锁对象
     */
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /**
     * 读操作的锁对象
     */
    private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    /**
     * 写操作的锁对象
     */
    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();


    /**
     * 读操作的方法
     *
     * @param key 读具体哪个的值
     * @return 获得值
     */
    public void get(String key) {
        //使用读锁对象对读操作加锁
        readLock.lock();
        log.info("{} 已进行读操作加锁，开始进行读操作。。。。", Thread.currentThread().getName());
        try {
            //模拟读操作
            Thread.sleep(1000);
            log.info("{} 读取到的值为: {}", Thread.currentThread().getName(), map.get(key));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            log.info("{} 读操作解锁,读操作结束。。。。",Thread.currentThread().getName());
        }
    }


    /**
     * 写操作
     *
     * @param key   需写入的key
     * @param value 需写入的value
     */
    public void put(String key, String value) {
        //使用写对象对写操作加锁
        writeLock.lock();
        try {
            log.info("{} 已进行写操作加锁，开始进行写操作。。。。",Thread.currentThread().getName());
            //模拟写操作
            Thread.sleep(3000);
            log.info("{} 进行写操作,写操作前的值为 {}",Thread.currentThread().getName(),map.get(key));
            map.put(key, value);
            log.info("{} 进行写操作,写操作后的值为 {}",Thread.currentThread().getName(),map.get(key));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
            log.info("{} 写操作解锁，写操作结束。。。。",Thread.currentThread().getName());
        }
    }


    public static void main(String[] args) {
        MultiThread10 readWriteLockDemo = new MultiThread10();
        readWriteLockDemo.put("key1", "value1");
        new Thread(() -> {
            readWriteLockDemo.put("key1", "value2");
        }, "写线程1").start();
        new Thread(() -> {
            readWriteLockDemo.put("key1", "value3");
        }, "写线程2").start();
        new Thread(() -> {
            readWriteLockDemo.get("key1");
        }, "读线程1").start();
        new Thread(() -> {
            readWriteLockDemo.get("key1");
        }, "读线程3").start();

    }
}
