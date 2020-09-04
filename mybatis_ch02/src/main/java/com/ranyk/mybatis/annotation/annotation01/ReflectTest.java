package com.ranyk.mybatis.annotation.annotation01;

/**
 * ClassName:ReflectTest
 * Description:Reflect注解测试
 *
 * @author ranyi
 * @date 2020-08-06 15:41
 * Version: V1.0
 */
public class ReflectTest {

    @Reflect
    public  static void sayHello(final String name){
        System.out.println("第一个 ===>> Hi, "+ name + " [sayHello]");
    }

    @Reflect(name = "AngelaBaby")
    public  static void sayHelloToSomeone(final String name){
        System.out.println("第二个 ===>> Hi, "+ name + " [sayHelloToSomeone]");
    }

    @Reflect(name = "dog")
    public static void sayHelloToSomething(String name){
        System.out.println("第三个 ===>> Hi, " + name + "[sayHelloToSomething]");
    }

    public static void main(String[] args) {
        ReflectProcessor reflectProcessor = new ReflectProcessor();
        try {
            reflectProcessor.parseMethod(ReflectTest.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
