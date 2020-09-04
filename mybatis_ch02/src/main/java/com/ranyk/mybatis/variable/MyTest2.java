package com.ranyk.mybatis.variable;

/**
 * ClassName:MyTest3
 * Description:成员变量测试二
 *
 * @author ranyi
 * @date 2020-06-11 16:32
 * Version: V1.0
 */
public class MyTest2 {

    public static void main(String[] args) {
        //MyTest1 myTest2 = new MyTest1();
        //myTest2.a = 1;
        //myTest2.aa();
        //myTest2.bb();

        /*Character[] characters = new Character[10];
        for (int i = 0; i < characters.length; i++) {
            System.out.println("characters中第"+i+"个元素的值为："+characters[i]);
        }
        String[] strs = new String[10];
        for (int i = 0; i < strs.length; i++) {
            System.out.println("strs中第"+i+"个元素的值为："+strs[i]);
        }*/

        for (int i = 0; ; i++) {

            if (i == 10) {
                System.out.println("for break");
                break;
            }

            System.out.println("当前的i = " + i);

        }

    }

}
