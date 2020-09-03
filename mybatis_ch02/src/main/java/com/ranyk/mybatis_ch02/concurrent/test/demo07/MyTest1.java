package com.ranyk.mybatis_ch02.concurrent.test.demo07;

import java.util.Random;
import java.util.concurrent.*;

/**
 * ClassName:MyTest1
 * Description:Future学习一
 *
 * @author ranyi
 * @date 2020-06-13 2:13
 * Version: V1.0
 */
public class MyTest1 {

    public static void main(String[] args) {

        //1.创建一个Callable实例
        Callable<Integer> callable = () -> {

            System.out.println("pre execution");

            Thread.sleep(5000);

            int i = new Random().nextInt(500);

            System.out.println("post execution");

            return i;
        };

        //2.创建Future实例
        FutureTask<Integer> future = new FutureTask<Integer>(callable);

        //3.在主线程中创建子线程对象，并启动子线程
        new Thread(future).start();

        System.out.println("thread has started");

        //4.主线程获取子线程的返回值
        try {
            Thread.sleep(2000);
            //System.out.println("获取到的线程执行结果为：" + future.get());
            /*thread has started
            pre execution
            主线程会在这里等待几秒，然后继续往下执行
            post execution
            获取到的线程执行结果为：53*/
            System.out.println("获取到的线程执行结果为：" + future.get(1, TimeUnit.MILLISECONDS));
            /*thread has started
pre execution
主线程在等待指定时间之后，仍未获得对应的子线程执行结果，则会抛出超时异常
java.util.concurrent.TimeoutException
	at java.util.concurrent.FutureTask.get(FutureTask.java:205)
	at com.ranyk.mybatis_ch02.concurrent.test.demo07.MyTest1.main(MyTest1.java:47)
主线程抛出异常，但是并不影响子线程的执行，所以子线程仍能继续往下执行
post execution*/

        } catch (TimeoutException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
