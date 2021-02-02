package com.ranyk.mybatis.concurrent.test.demo12;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * CLASS_NAME: MultiThread06<br/>
 * Description: 多线程示例 - ThreadLocal 使用<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Data
@Slf4j
public class MultiThread06 {

    public static void main(String[] args) {
        Bank2 bank2 = new Bank2("测试账户");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                3,
                300,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(3),
                new ThreadPoolExecutor.CallerRunsPolicy());
        Callable<Integer> expenses = new Expenses(bank2);
        Callable<Integer> earnings = new Earnings(bank2);
        Future<Integer> expensesTask = executor.submit(expenses);
        Future<Integer> earningsTask = executor.submit(earnings);
        executor.shutdown();
        while (true) {
            if (executor.isTerminated()) {
                try {
                    log.info("支出账户支出后余额为: {} , 收益账户收益后余额为: {} , 账户的最后余额为 {} ", expensesTask.get(), earningsTask.get(), bank2.getBalance());
                } catch (Exception e) {
                    log.error("获取线程返回结果发生异常,异常信息为: {}", e.getMessage());
                }
                break;
            }
        }

    }

}


/**
 * 线程安全账户对象类
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
class Bank {
    /**
     * 账户名
     */
    private String name;
    /**
     * 账户余额
     */
    private ThreadLocal<Integer> balance = ThreadLocal.withInitial(() -> 0);


    /**
     * 初始化账户对象构造方法
     *
     * @param name 账户名
     */
    public Bank(String name) {
        this.name = name;
    }


    /**
     * 入账方法
     *
     * @param amount 入账金额
     */
    public void credit(Integer amount) {
        log.info("线程 {} 中 {} 账户入账之前的金额: {}", Thread.currentThread().getName(), this.name, this.balance.get());
        this.balance.set(this.balance.get() + amount);
        log.info("线程 {} 中 {} 账户入账之后的金额: {}", Thread.currentThread().getName(), this.name, this.balance.get());
    }


    /**
     * 出账方法
     *
     * @param amount 出账金额
     */
    public void payOut(Integer amount) {
        if (this.balance.get() == null || this.balance.get() < amount) {
            log.error("线程 {} 中 {} 账户金额不足,不予支付!", Thread.currentThread().getName(), this.name);
            balance.remove();
            return;
        }
        log.info("线程 {} 中 {} 账户出账之前的金额: {}", Thread.currentThread().getName(), this.name, this.balance.get());
        this.balance.set(this.balance.get() - amount);
        log.info("线程 {} 中 {} 账户出账之后的金额: {}", Thread.currentThread().getName(), this.name, this.balance.get());
    }

}


/**
 * 非线程安全账户对象类
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
class Bank2 {
    /**
     * 账户名
     */
    private String name;
    /**
     * 账户余额
     */
    private Integer balance = 0;


    /**
     * 初始化账户对象构造方法
     *
     * @param name 账户名
     */
    public Bank2(String name) {
        this.name = name;
    }


    /**
     * 入账方法
     *
     * @param amount 入账金额
     */
    public void credit(Integer amount) {
        //log.info("线程 {} 中 {} 账户入账之前的金额: {}", Thread.currentThread().getName(), this.name, this.balance);
        this.balance = (this.balance + amount);
        //log.info("线程 {} 中 {} 账户入账之后的金额: {}", Thread.currentThread().getName(), this.name, this.balance);
    }


    /**
     * 入账方法
     *
     * @param amount 入账金额
     * @param count  入账次数
     */
    public void credit(Integer amount, int count) {
        log.info("线程 {} 中,第 {} 次 {} 账户入账之前的金额: {}", Thread.currentThread().getName(), count, this.name, this.balance);
        this.balance = (this.balance + amount);
        log.info("线程 {} 中,第 {} 次 {} 账户入账之后的金额: {}", Thread.currentThread().getName(), count, this.name, this.balance);
    }


    /**
     * 出账方法
     *
     * @param amount 出账金额
     */
    public void payOut(Integer amount) {
        //log.info("线程 {} 中 {} 账户出账之前的金额: {}", Thread.currentThread().getName(), this.name, this.balance);
        this.balance = (this.balance - amount);
        //log.info("线程 {} 中 {} 账户出账之后的金额: {}", Thread.currentThread().getName(), this.name, this.balance);
    }
}


/**
 * 支出线程类
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
class Expenses implements Callable<Integer> {

    private Bank2 bank2;

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        for (int i = 1; i <= 10; i++) {
            bank2.payOut(1);
        }
        return bank2.getBalance();
    }
}


/**
 * 收益线程类
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
class Earnings implements Callable<Integer> {

    private Bank2 bank2;

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        for (int i = 1; i <= 10; i++) {
            bank2.credit(1);
        }
        return bank2.getBalance();
    }
}