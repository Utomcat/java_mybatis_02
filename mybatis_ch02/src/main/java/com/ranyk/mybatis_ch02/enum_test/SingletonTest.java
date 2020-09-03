package com.ranyk.mybatis_ch02.enum_test;

/**
 * ClassName:SingletonTest
 * Description:单例测试类
 *
 * @author ranyi
 * @date 2020-05-30 11:21
 * Version: V1.0
 */
public class SingletonTest {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        System.out.println(instance == instance2 ? "两者是同一个实例":"两者不是同一个实例");

    }

}
