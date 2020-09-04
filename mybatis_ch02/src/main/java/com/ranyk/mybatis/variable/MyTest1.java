package com.ranyk.mybatis.variable;

/**
 * ClassName:MyTest1
 * Description:
 * 测试成员变量的范围即在不同地方进行操作后，是否能在其他地方获取到操作后的值
 * 能够实现在某个方法中对成员变量进行操作后在另一个地方获取的值是操作后的值
 *
 * @author ranyi
 * @date 2020-06-11 10:56
 * Version: V1.0
 */
public class MyTest1 {

    int a  = 0;

    public  void aa() {
        System.out.println("aa操作前 a = " + a);
        a++;
        System.out.println("aa操作后 a = " + a);

    }

    public  void bb() {
        System.out.println("bb操作前 a = " + a);
        a++;
        System.out.println("bb操作后 a = " + a);

    }

    public static void main(String[] args) {
        MyTest1 myTest2 = new MyTest1();
        myTest2.aa();
        myTest2.bb();
        myTest2.aa();
    }

}
