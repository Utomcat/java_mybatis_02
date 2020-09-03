package com.ranyk.mybatis_ch02.variable;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName:MyTest7
 * Description:常量测试
 *
 * @author ranyi
 * @date 2020-06-23 12:49
 * Version: V1.0
 */
public class MyTest7 {

    //静态常量

    private static final AtomicInteger I = new AtomicInteger(0);

    private final AtomicInteger J = new AtomicInteger(0);

    private final  int  M = 0;

    private  static final int N = 0;

    private  static final String STR = "aa";

    private static final String STR2 = new String("bbb");

    public static void main(String[] args) {
        MyTest7 myTest7 = new MyTest7();
        //System.out.println("操作前 i = " + MyTest7.I.get());
        //System.out.println("操作前 j = " + myTest7.J.get());
        //System.out.println("操作前 m = " + myTest7.M);
        //System.out.println("操作前 n = " + MyTest7.N);
        //System.out.println("操作前 str = " + MyTest7.STR);
        MyTest7.I.getAndIncrement();
        //myTest7.J.getAndIncrement();
        //myTest7.M++;
        //MyTest7.n++;
        //MyTest7.str = MyTest7.str + "bbb";
        //System.out.println("操作后 i = " + MyTest7.I.get());
        //System.out.println("操作后 j = " + myTest7.J.get());
        //System.out.println("操作后 m = " + myTest7.M);
        //System.out.println("操作后 n = " + MyTest7.N);
    }

}
