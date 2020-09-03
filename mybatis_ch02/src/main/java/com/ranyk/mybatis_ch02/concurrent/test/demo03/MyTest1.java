package com.ranyk.mybatis_ch02.concurrent.test.demo03;

/**
 * ClassName:MyTest1
 * Description:synchronized关键字学习一
 * synchronized关键字的使用方式
 * 1、修饰一个代码块，语法格式：
 * synchronized(obj){}
 * @author ranyi
 * @date 2020-06-07 23:51
 * Version: V1.0
 */
public class MyTest1 {

    private Object obj = new Object();

    public void method(){
        System.out.println("================== before print hello world ==================");

        //使用synchronized同步代码块的时候，对应的synchronized(obj)中的锁对象 obj 可以是任意的一个对象，但是为了通用故使用Object对象
        synchronized (obj){
            System.out.println("打印中："+"hello world");
            throw new RuntimeException();
        }
        //System.out.println("================== after print hello world ==================");
    }


    public void method2(){
        synchronized (obj){
            System.out.println("welcome");
        }
    }

}
