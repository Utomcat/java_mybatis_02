package com.ranyk.mybatis_ch02.exception;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName:ExceptionTest
 * Description:异常测试类
 *
 * @author ranyi
 * @date 2020-08-11 15:12
 * Version: V1.0
 */
public class ExceptionTest {

    public static void main(String[] args) {

        try {
            ExceptionTest exceptionTest = new ExceptionTest();
            exceptionTest.method1(1);
            exceptionTest.method2();
            exceptionTest.method3("aaaa");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("错误信息： " + e.getMessage());
            System.out.println("Cause: " + e.getCause());
            System.out.println("LocalizedMessage: " + e.getLocalizedMessage());
            System.out.println("Class: " + e.getClass());
        }


    }

    private List<Integer> method1(Integer a) throws Exception {

        Integer[] integers = {1,2,3,4,5};

        List<Integer> lists = Arrays.asList(integers);

        if (a <= 0){
            throw new Exception("参数不合法");
        }


        return lists;
    }

    private void method2(){
        System.out.println("aaaaaa");
    }

    private String method3(String s){

        Integer integer = Integer.valueOf(s);

        return String.valueOf(integer);
    }

}
