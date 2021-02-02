package com.ranyk.mybatis.concurrent.test.demo12;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * CLASS_NAME: MultiThread11<br/>
 * Description: 多线程示例 - ThreadPoolExecutor 使用示例: 验证 使用 ThreadPoolExecutor 提交实现 Runnable 接口的任务返回结果<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Data
@Slf4j
public class MultiThread11 {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                3,
                300,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(3),
                new ThreadPoolExecutor.CallerRunsPolicy());
        RunnableTest run = new RunnableTest();
        Future<?> submit = executor.submit(run);
        executor.shutdown();
        while (true){
            if (executor.isTerminated()){
                try {
                    log.info("子线程的执行结果: {}",submit.get());
                } catch (InterruptedException | ExecutionException e) {
                    log.error("获取子线程执行结果时出错,错误信息为: {}",e.getMessage());
                }
                break;
            }
        }
    }
}


@Data
@Slf4j
class RunnableTest implements Runnable{

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
        log.info("ThreadPoolExecutor 线程池测试, 当前线程为: {}",Thread.currentThread().getName());
    }
}
