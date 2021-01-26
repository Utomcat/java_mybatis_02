package com.ranyk.mybatis.concurrent.test.demo12;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * CLASS_NAME: MultiThread03<br/>
 * Description: 多线程示例三 - CountDownLatch 使用练习<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Data
@Slf4j
public class MultiThread03 {
    /**
     * 计数器的个数
     */
    private CountDownLatch count = new CountDownLatch(3);


    /**
     * 运动员线程
     */
    public void racer() {
        log.info("{} 开始准备...",Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            log.error("{} 线程出现异常,异常信息为: {}",Thread.currentThread().getName(),e.getMessage());
        }
        log.info("{} 准备结束.",Thread.currentThread().getName());
        count.countDown();
    }


    /**
     * 教练线程
     */
    public void coach() {
        log.info("{} 开始等待...",Thread.currentThread().getName());
        try {
            count.await();
        }catch (Exception e){
            log.error("{} 线程出现异常,异常信息为: {}",Thread.currentThread().getName(),e.getMessage());
        }
        log.info("{} 等待结束.",Thread.currentThread().getName());
        count.countDown();
    }


    /**
     * 测试main方法
     * @param args main方法自带参数
     */
    public static void main(String[] args) {
        MultiThread03 thread03 = new MultiThread03();
        new Thread(() -> {
            thread03.racer();
        },"运动员一").start();
        new Thread(() -> {
            thread03.racer();
        },"运动员二").start();
        new Thread(() -> {
            thread03.racer();
        },"运动员三").start();
        new Thread(() -> {
            thread03.coach();
        },"教练").start();
    }
}
