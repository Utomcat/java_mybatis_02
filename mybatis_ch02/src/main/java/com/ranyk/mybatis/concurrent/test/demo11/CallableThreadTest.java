package com.ranyk.mybatis.concurrent.test.demo11;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * CLASS_NAME: CallableThreadTest<br/>
 * Description: Callable方式创建线程<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Data
@Slf4j
public class CallableThreadTest implements Callable<Integer> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (; i < 100; i++) {
            log.info("{} ==> {}",Thread.currentThread().getName(),i);
        }
        return i;
    }

    @Test
    public void test01() {
        CallableThreadTest ctt = new CallableThreadTest();
        FutureTask<Integer> ft = new FutureTask<>(ctt);
        for (int i = 0; i < 100; i++) {
            log.info("{} ==> {}",Thread.currentThread().getName(),i);
            if (i==20){
                new Thread(ft,"有返回值的线程").start();
            }
        }
        try {
            log.info("子线程的返回值： {}",ft.get());
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }


}
