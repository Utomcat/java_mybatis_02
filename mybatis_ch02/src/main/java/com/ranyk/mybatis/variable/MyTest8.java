package com.ranyk.mybatis.variable;

import lombok.Data;

/**
 * ClassName:MyTest8
 * Description:静态变量的测试
 *
 * @author ranyi
 * @date 2020-10-21 14:12
 * Version: V1.0
 */
@Data
public class MyTest8 {

    private  static  String str;


    public static void setStrMethod(String s){
        str = s;
    }


    public void method0(){
        System.out.println(" 方法执行前,还未调用静态的设值方法的值 ===> " + str);

        System.out.println("=============== 调用设值方法 star==============");
        MyTest8.setStrMethod("aaa");
        System.out.println("=============== 调用设值方法 end===============");

        System.out.println(" 方法执行后,调用静态的设值方法的值 ===> " + str);
    }



    public void method01(){
        System.out.println("在其他地方使用该属性的值 ===> " + str);
    }


    public static void main(String[] args) {
        MyTest8 myTest8 = new MyTest8();
        myTest8.method01();
        myTest8.method0();
        myTest8.method01();

    }


}
