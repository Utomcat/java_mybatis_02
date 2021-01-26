package com.ranyk.mybatis.concurrent.test.demo11;


import lombok.Data;

/**
 * CLASS_NAME: MyTest1<br/>
 * Description: volatile关键字测试<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
public class MyTest1 {
    private volatile Singleton instance = new Singleton();

}

@Data
class Singleton{
    private int age;

}

class FinalExample{
    int i;
    final int j;
    static FinalExample obj;

    public FinalExample() {
        this.i = 1;
        this.j = 2;
    }

    public static void write() {
        obj = new FinalExample();
    }

    public static void reader() {
        FinalExample object = obj;
        int a = object.i;
        int b = object.j;
    }
}