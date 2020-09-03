package com.ranyk.mybatis_ch02.process.forloop;

/**
 * ClassName:ForLoopTest
 * Description:For循环结构测试
 *
 * @author ranyi
 * @date 2020-07-27 17:34
 * Version: V1.0
 */
public class ForLoopTest {

    public static void main(String[] args) {
        ForLoopTest forLoopTest = new ForLoopTest();
        forLoopTest.continueCondition();
    }


    /**
     * for 的双重循环测试一：
     *  在内层循环中使用break的测试
     *  结束内层循环，跳至进行下一次的外层循环：即结束内层循环，执行下一次的外层循环
     */
    public void twoLoop(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 3 && i == 7){
                    System.out.println(i + ", " + j);
                    break;
                }
            }
        }
    }

    /**
     * for循环中使用continue的测试
     * 遇到continue的时候当次循环终止，进入到下一次的循环中
     */
    public void continueCondition(){
        for (int i = 0; i < 10; i++) {
            if (i <= 3){
                continue;
            }
            System.out.println(i);
        }
    }


}
