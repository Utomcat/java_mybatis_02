package com.ranyk.mybatis.concurrent.test.demo03;

/**
 * ClassName:MyTest3
 * Description:synchronized关键字学习三
 *
 * @author ranyi
 * @date 2020-06-08 11:06
 * Version: V1.0
 */
public class MyTest3 {

    public static  synchronized void method(){
        System.out.println("hello world");
    }
}
