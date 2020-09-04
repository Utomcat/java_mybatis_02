package com.ranyk.mybatis.mianshi;

import java.util.Scanner;

/**
 * ClassName:Main
 * Description:2020-6-16面试
 *
 * @author ranyi
 * @date 2020-06-16 9:32
 * Version: V1.0
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(fibonacci(n));

    }

    public static int fibonacci(int num) {
        return num <= 1 ? num : fibonacci(num - 1) + fibonacci(num - 2);
    }

}
