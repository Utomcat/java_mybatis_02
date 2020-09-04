package com.ranyk.mybatis.variable;

/**
 * ClassName:MyTest5
 * Description:递归的算法
 *
 * @author ranyi
 * @date 2020-06-17 18:40
 * Version: V1.0
 */
public class MyTest5 {

    public static void main(String[] args) {

        int n = 0;

        MyTest5 myTest5 = new MyTest5();

        System.out.println( n + "的阶乘为：" + myTest5.factorial(n));
        System.out.println("等差数列的前"+ n +"项的和为：" + myTest5.plus(n));

    }

    /**
     * 递归方式计算的阶乘算法
     * @param n 需要计算阶乘的数
     * @return 返回计算结果
     */
    public int factorial(int n){
        return n == 0 ? 1 : n * factorial(n-1);
    }

    /**
     * 公差为 1 的等差数列的前 N 项和
     * @param n 计算第几项的和
     * @return 返回计算出的前 N 项的和
     */
    public int plus(int n){
        return n == 0 ? 0 : n + plus(n-1);
    }




}
