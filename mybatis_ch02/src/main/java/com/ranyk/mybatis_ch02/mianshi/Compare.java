package com.ranyk.mybatis_ch02.mianshi;

/**
 * ClassName:Compare
 * Description:
 *
 * @author ranyi
 * @date 2020-06-02 20:01
 * Version: V1.0
 */
public interface Compare {
    int max(int[] nums);
    int max(int num1, int num2);
    int max(int num1, int num2, int num3);
    int max(int num1, int num2, int num3, int num4, int num5);
}