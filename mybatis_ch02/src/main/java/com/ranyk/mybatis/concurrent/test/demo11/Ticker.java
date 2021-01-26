package com.ranyk.mybatis.concurrent.test.demo11;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CLASS_NAME: Ticketer<br/>
 * Description: 售票类<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Data
@Slf4j
public class Ticker implements Runnable {

    private int number = 100;
    /**
     * 创建锁对象
     */
    private final Object obj = new Object();
    /**
     * 创建可重入锁对象
     */
    private Lock lock = new ReentrantLock(true);

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        do {
            //调用具体的业务方法
            saleTic0();
        } while (number > 0);
    }

    /**
     * 业务执行方法
     */
    private void saleTic0(){
        if (number > 0) {
            try {
                Thread.sleep(100);
                log.info("线程：" + Thread.currentThread().getName() + "，余票：" + number + "，买后剩余：" + (--number));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            log.info("线程：" + Thread.currentThread().getName() + " 已售完,已没余票!");
        }
    }

    /**
     * 使用同步代码块的方式进行线程安全控制
     */
    private void saleTic1() {
        synchronized (obj) {
            //同步代码块
            saleTic0();
        }
    }

    /**
     * 使用同步方法的方式进行线程安全控制
     */
    private synchronized void saleTic2() {
        saleTic0();
    }

    /**
     * 使用可重入锁方式进行线程安全控制
     */
    private void saleTic3(){
        lock.lock();
        try{
            saleTic0();
        }finally {
            lock.unlock();
        }
    }

}

class Test {
    public static void main(String[] args) {
        Ticker ticker = new Ticker();
        Thread thread0 = new Thread(ticker, "窗口一");
        Thread thread01 = new Thread(ticker, "窗口二");
        Thread thread02 = new Thread(ticker, "窗口三");
        thread0.start();
        thread01.start();
        thread02.start();
    }
}
