package com.ranyk.mybatis_ch02.concurrent.test.demo03;

/**
 * ClassName:MyTest2
 * Description:synchronized关键字学习二
 *
 * @author ranyi
 * @date 2020-06-08 10:50
 * Version: V1.0
 */
public class MyTest2 {

    public  synchronized void method(){
        System.out.println("hello world");
    }

}
