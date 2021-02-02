package com.ranyk.mybatis.concurrent.test.demo12;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CLASS_NAME: MultiThread08<br/>
 * Description: 多线程示例 - 原子类使用二<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Data
@Slf4j
public class MultiThread08 {
    //private static int n = 0;
    private static AtomicInteger n = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        int j = 1;
        while (j <= 10) {
            Thread thread = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    n.incrementAndGet();
                }
            });
            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    n.incrementAndGet();
                }
            });
            thread.start();
            thread1.start();
            thread.join();
            thread.join();

            log.info("第 {} 次执行, n 的最终值为: {} ",j,n);
            j++;
        }
    }
}
