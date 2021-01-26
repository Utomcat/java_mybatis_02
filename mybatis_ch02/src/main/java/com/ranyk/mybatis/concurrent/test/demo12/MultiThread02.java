package com.ranyk.mybatis.concurrent.test.demo12;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CLASS_NAME: MultiThread02<br/>
 * Description: 多线程测试类二 - 使用Condition进行线程通信<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Data
@Slf4j
public class MultiThread02 {
    /**
     * 声明打印初始值
     */
    private int i = 0;
    /**
     * 声明打印数量
     */
    private int count = 10;
    /**
     * 声明锁对象
     */
    private Lock lock = new ReentrantLock();
    /**
     * 从锁对象中获取 Condition 对象
     */
    private Condition condition = lock.newCondition();


    /**
     * 奇数打印线程
     *
     * @throws InterruptedException 抛出中断异常
     */
    public void odd() throws InterruptedException {
        while (i < count) {
            lock.lock();
            try {
                if (i % 2 == 1) {
                    log.info("当前是奇数: " + i);
                    i++;
                    condition.signal();
                } else {
                    condition.await();
                }
            } finally {
                lock.unlock();
            }
        }
    }


    /**
     * 偶数打印线程
     *
     * @throws InterruptedException 抛出中断异常
     */
    public void even() throws InterruptedException {
        while (i < count) {
            lock.lock();
            try {
                if (i % 2 == 0) {
                    log.info("当前是偶数: " + i);
                    i++;
                    condition.signal();
                } else {
                    condition.await();
                }
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) {
        MultiThread02 thread02 = new MultiThread02();
        new Thread(() -> {
            try {
                thread02.odd();
            } catch (Exception e) {
                log.error("奇数线程出现异常 {}", e.getMessage());
            }
        }).start();

        new Thread(() -> {
            try {
                thread02.even();
            } catch (Exception e) {
                log.error("偶数线程出现异常: {}", e.getMessage());
            }

        }).start();
    }

}
