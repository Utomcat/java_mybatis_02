package com.ranyk.mybatis.concurrent.test.demo12;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CLASS_NAME: MultiThread07<br/>
 * Description: 多线程示例 - 原子类的使用一<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Data
@Slf4j
public class MultiThread07 {
    static AtomicStampedReference<Integer> atomicInteger;

    public static void main(String[] args) {
        try {


        int j = 0;
        while (j < 10) {
            atomicInteger = new AtomicStampedReference<>(0,0);
            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    int stamp;
                    Integer reference;
                    do {
                        stamp = atomicInteger.getStamp();
                        reference = atomicInteger.getReference();
                        //log.info("当前线程为 {} , 时间戳为 {} , atomicInteger 的值为: {}",Thread.currentThread().getName(),atomicInteger.getStamp(),atomicInteger.getReference());
                    } while (!atomicInteger.compareAndSet(reference, reference + 1, stamp, stamp + 1));
                }
            },"线程一");
            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    int stamp;
                    Integer reference;
                    do {
                        stamp = atomicInteger.getStamp();
                        reference = atomicInteger.getReference();
                        //log.info("当前线程为 {} , 时间戳为 {} , atomicInteger 的值为: {}",Thread.currentThread().getName(),atomicInteger.getStamp(),atomicInteger.getReference());
                    } while (!atomicInteger.compareAndSet(reference, reference - 1, stamp, stamp - 1));
                }
            },"线程二");
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            log.info("atomicInteger 的最终值: {}",atomicInteger.getReference());
            j++;
        }
        }catch (Exception e){
            log.error("发生异常 {}",e.getMessage());
        }
    }
}
