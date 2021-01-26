package com.ranyk.mybatis.concurrent.test.demo12;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * CLASS_NAME: MultiThread01<br/>
 * Description: 多线程测试类一<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Data
@Slf4j
public class MultiThread01 {

    /**
     * 声明初始需要打印的数
     */
    private int i = 0;
    /**
     * 声明静态的需要打印的起始数
     */
    private static int j = 0;
    /**
     * 声明对象
     */
    private final Object obj = new Object();
    /**
     * 总数
     */
    private static int count = 10;


    /**
     * 默认打印奇数的任务
     *
     * @throws InterruptedException 中断异常
     */
    public void odd0() throws InterruptedException {
        //判断当前需要打印的数是奇数还是偶数
        while (i < count) {
            if (i % 2 == 1) {
                log.info("当前是奇数：" + i);
                i++;
                obj.notify();//唤醒偶数线程打印
            } else {
                obj.wait();
            }
        }
    }


    /**
     * 默认打印偶数的任务
     *
     * @throws InterruptedException 中断异常
     */
    public void even0() throws InterruptedException {
        while (i < count) {
            if (i % 2 == 0) {
                log.info("当前是偶数：" + i);
                i++;
                obj.notify();//唤醒奇数线程打印
            } else {
                obj.wait();
            }
        }
    }


    /**
     * 通过 synchronized 修饰方法方式的奇数打印方法,由奇数线程调用
     */
    public synchronized void oddOther() throws InterruptedException {
        //判断当前需要打印的数是奇数还是偶数
        while (i < count) {
            if (i % 2 == 1) {
                log.info("当前是奇数：" + i);
                i++;
                this.notify();//唤醒偶数线程打印
            } else {
                this.wait();
            }
        }
    }


    /**
     * 通过 synchronized 修饰方法方式的偶数打印方法,由偶数线程调用
     */
    public synchronized void evenOther() throws InterruptedException {
        while (i < count) {
            if (i % 2 == 0) {
                log.info("当前是偶数：" + i);
                i++;
                this.notify();//唤醒奇数线程打印
            } else {
                this.wait();
            }
        }
    }


    /**
     * 通过 synchronized 修饰静态方法方式的奇数打印方法,由奇数线程调用
     */
    public static synchronized void oddOther2() throws InterruptedException {
        while (j < count) {
            if (j % 2 == 1) {
                log.info("当前是奇数：" + j);
                j++;
                MultiThread01.class.notify();//唤醒偶数线程打印
            } else {
                MultiThread01.class.wait();
            }
        }
    }


    /**
     * 通过 synchronized 修饰方法方式的偶数打印方法,由偶数线程调用
     */
    public static synchronized void evenOther2() throws InterruptedException {
        while (j < count) {
            if (j % 2 == 0) {
                log.info("当前是偶数：" + j);
                j++;
                MultiThread01.class.notify();//唤醒奇数线程打印
            } else {
                MultiThread01.class.wait();
            }
        }
    }


    /**
     * 通过synchronized修饰代码块方式的,奇数打印方法.由奇数线程调用
     */
    public void odd1() throws InterruptedException {
        synchronized (obj) {
            odd0();
        }
    }


    /**
     * 通过synchronized修饰代码块方式的,偶数打印方法.由偶数线程调用
     */
    public void even1() throws InterruptedException {
        //判断当前需要打印的数是奇数还是偶数
        synchronized (obj) {
            even0();
        }

    }


    /**
     * 测试方法
     *
     * @param args main 方法默认参数
     */
    public static void main(String[] args) {
        //创建线程任务对象
        MultiThread01 oddEvenDemo = new MultiThread01();
        //创建线程对象，开启奇数线程打印
        new Thread(() -> {
            try {
                oddEvenDemo.oddOther();
            } catch (InterruptedException e) {
                log.info(e.getMessage());
            }
        }).start();
        //创建线程对象，开启偶数线程打印
        new Thread(() -> {
            try {
                oddEvenDemo.evenOther();
            } catch (InterruptedException e) {
                log.info(e.getMessage());
            }

        }).start();
    }

}
