package com.ranyk.mybatis_ch02.variable;

/**
 * ClassName:MyTest3
 * Description:变量
 *
 * ++i 和  i++ 的区别在于前者是先加再赋值  后者先赋值在加
 *
 * @author ranyi
 * @date 2020-06-11 21:06
 * Version: V1.0
 */
public class MyTest3 {

    private int i = 0;

    public static void main(String[] args) {
        MyTest3 myTest3 = new MyTest3();
        System.out.println("操作前：i = " + myTest3.i);//打印值为0
        System.out.println("操作：i++ = " + (myTest3.i++));//打印值为0
        System.out.println("操作后：i = " + myTest3.i);//打印值为1
        System.out.println("=========================================");
        System.out.println("操作前：i = " + myTest3.i);//打印值为1
        System.out.println("操作：++i = " + (++myTest3.i));//打印值为2
        System.out.println("操作后：i = " + myTest3.i);//打印值为2

    }
}
