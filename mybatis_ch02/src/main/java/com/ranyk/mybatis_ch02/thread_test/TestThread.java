package com.ranyk.mybatis_ch02.thread_test;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ClassName:TestThread
 * Description:线程测试类
 *
 * @author ranyi
 * @date 2020-05-30 13:53
 * Version: V1.0
 */
public class TestThread {

    public static void main(String[] args) {
        //ThreadDemo01 threadDemo01 = new ThreadDemo01("Thread-1");
        //ThreadDemo01 threadDemo02 = new ThreadDemo01("Thread-2");
        //threadDemo01.start();
        //threadDemo02.start();

        /*ThreadDemo02 threadDemo01 = new ThreadDemo02("Thread-1");
        ThreadDemo02 threadDemo02 = new ThreadDemo02("Thread-2");
        threadDemo01.start();
        threadDemo02.start();*/

        ThreadDemo03 threadDemo01 = new ThreadDemo03("Thread-1");
        ThreadDemo03 threadDemo02 = new ThreadDemo03("Thread-2");
        Map<String,Object> start = threadDemo01.start(threadDemo01);
        Map<String,Object> start1 = threadDemo02.start(threadDemo02);


        try {
            System.out.println("子线程1的名称为："+start.get("threadName")+"子线程1的返回值为："+((FutureTask)start.get("futureTask")).get());
            System.out.println("子线程2的名称为："+start1.get("threadName")+"子线程2的返回值为："+((FutureTask)start1.get("futureTask")).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
