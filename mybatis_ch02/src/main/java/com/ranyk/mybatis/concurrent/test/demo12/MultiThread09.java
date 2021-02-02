package com.ranyk.mybatis.concurrent.test.demo12;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * CLASS_NAME: MultiTread09<br/>
 * Description: 多线程示例 - 可重入锁练习使用<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Slf4j
public class MultiThread09 {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();//此处会提示代码警告：锁【lock.lock】必须紧跟try代码块，且unlock要放到finally第一行。
                log.info("加锁次数: {}",(i+1));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
            try{
                log.info("解锁次数: {}",(i+1));
            }finally {
                lock.unlock();
            }
        }

    }

}
