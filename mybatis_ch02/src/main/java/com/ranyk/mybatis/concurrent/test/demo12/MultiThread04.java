package com.ranyk.mybatis.concurrent.test.demo12;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CLASS_NAME: MultiThread04<br/>
 * Description: 多线程示例四 - CyclicBarrier 使用练习<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Data
@Slf4j
public class MultiThread04 {
    /**
     * 创建循环屏障
     */
    private CyclicBarrier barrier = new CyclicBarrier(3);


    /**
     * 工作线程
     */
    public void startT() {
        log.info("线程 {} 准备启动...", Thread.currentThread().getName());
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            log.error("{} 线程出现异常,异常原因: {}", Thread.currentThread().getName(), e.getMessage());
        }
        log.info("线程 {} 启动完毕,启动时间 {}", Thread.currentThread().getName(), System.currentTimeMillis());
    }


    /**
     * main测试方法
     *
     * @param args main方法默认参数
     */
    public static void main(String[] args) {
        MultiThread04 thread04 = new MultiThread04();
        new Thread(() -> {
            thread04.startT();
        }, "线程一").start();
        new Thread(() -> {
            thread04.startT();
        }, "线程二").start();
        new Thread(() -> {
            thread04.startT();
        }, "线程三").start();
    }

}
