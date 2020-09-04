package com.ranyk.mybatis.concurrent.test.demo07;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:MyTest2
 * Description:CompletableFuture学习一
 *
 * 使用CompletableFuture能保证程序执行的异步性和获取结果的异步性
 *
 * @author ranyi
 * @date 2020-06-13 12:24
 * Version: V1.0
 */
public class MyTest2 {

    public static void main(String[] args) {
        //对结果进行变换   运行结果 hello world
        /*String result = CompletableFuture.supplyAsync(() -> "hello").thenApplyAsync(value -> value + " world").join();
        System.out.println(result);

        System.out.println("=========================");

        //对结果进行消费   运行结果 welcome hello
        CompletableFuture.supplyAsync(() -> "hello").thenAccept(value -> System.out.println("welcome " + value));

        System.out.println("=========================");

        // 对于stage的合并操作   运行结果  result2 = hello  world
        String result2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return " world";
        }),(s1,s2) -> s1 + " " + s2).join();

        System.out.println("result2 = " + result2);*/


        // 执行结果
        // =========================
        //主线程执行完成！
        // 在此处会等待几秒
        //task finished
        //执行完成

        System.out.println("=========================");

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("task finished");
        });

        // 此处是不会发生阻塞的
        completableFuture.whenComplete((t,action) -> System.out.println("执行完成！"));

        System.out.println("主线程执行完成！");

        try {
            TimeUnit.MILLISECONDS.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
