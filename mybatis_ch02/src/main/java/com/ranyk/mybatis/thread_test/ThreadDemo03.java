package com.ranyk.mybatis.thread_test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * ClassName:ThreadDemo03
 * Description: 线程的创建类三  使用实现Callable接口的方式创建
 *  如果不指定返回值类型及不 implements Callable<Integer> 这样写，则返回值是 Object 对象
 * @author ranyi
 * @date 2020-06-02 15:43
 * Version: V1.0
 */
public class ThreadDemo03 implements Callable<Integer> {


    private Thread thread;
    private String threadName;

    public ThreadDemo03(String threadName) {
        this.threadName = threadName;
        System.out.println("Creating Thread：" + threadName);
    }


    @Override
    public Integer call() throws Exception {

        Integer integer = new Integer(0);

        System.out.println("Running " + threadName);

        try {
            for (int i = 0; i < 4; i++) {
                System.out.println("Thread：" + threadName + ",当前i的值为：" + i);
                Thread.sleep(50);
                integer = i;
            }
            return integer;
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
            return 0;
        }finally {
            System.out.println("Thread " + threadName + " exiting.");
        }
    }

    /**
     * 启动线程
     * @param thread3 传入的实现Callable接口的对象
     * @return 返回FutureTask对象
     */
    public Map<String,Object> start(ThreadDemo03 thread3) {
        System.out.println("Starting " + threadName + " 启动线程.");
        FutureTask<Integer> futureTask = new FutureTask<>(thread3);
        if (thread == null){
            thread = new Thread(futureTask,threadName);
        }
        thread.start();

        Map<String,Object> map = new HashMap<>(16);
        //获取线程的名称 除使用Thread.currentThread().getName() 还可使用 Thread 对象的 getName()方法 下面的 thread 就是一个Thread的实例对象
        map.put("threadName",thread.getName());
        map.put("futureTask",futureTask);

        return map;
    }

}
