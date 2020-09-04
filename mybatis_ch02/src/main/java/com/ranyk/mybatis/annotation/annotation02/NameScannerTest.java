package com.ranyk.mybatis.annotation.annotation02;

/**
 * ClassName:NameScannerTest
 * Description: 编译时注解测试 编译时的没有运行成功
 *
 * @author ranyi
 * @date 2020-08-06 15:54
 * Version: V1.0
 */
@NameScanner
public class NameScannerTest {

    @NameScanner
    private String name;

    @NameScanner
    private int age;

    @NameScanner
    public String getName(){
        return this.name;
    }

    @NameScanner
    public void setName(String name){
        this.name = name;
    }

    public static void main(String[] args){
        System.out.println("--finished--");
    }

}
