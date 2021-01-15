package com.ranyk.mybatis.enum_test;

/**
 * ClassName:Singleton
 * Description:单例模式演示
 *
 * @author ranyi
 * @date 2020-05-30 11:19
 * Version: V1.0
 */
public enum  Singleton {

    /**
     * instance 常量
     */
    INSTANCE;


    Singleton(){
        System.out.println("初始化单例类！");
    }

    public static Singleton getInstance(){
        return INSTANCE;
    }

}
