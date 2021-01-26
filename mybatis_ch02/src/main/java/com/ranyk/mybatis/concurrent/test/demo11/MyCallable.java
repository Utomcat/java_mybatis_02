package com.ranyk.mybatis.concurrent.test.demo11;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * CLASS_NAME: MyCallable<br/>
 * Description: CallAble接口实现<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Data
@Slf4j
public class MyCallable implements Callable {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Object call() throws Exception {
        if (3==2){
            return 99;
        }else{
            throw new RuntimeException("测试异常");
        }
    }

    @Test
    public void test01() {
        Callable callable = new MyCallable();
        FutureTask<Integer> task = new FutureTask<>(callable);
        new Thread(task).start();

        try {
            // 得到返回值
            log.info("获取到的结果 {}",task.get());
        } catch (InterruptedException | ExecutionException e) {
            /* 当阻塞方法收到中断请求的时候就会抛出InterruptedException异常 或 处理线程抛出的异常 */
            e.printStackTrace();
        }
    }
}
