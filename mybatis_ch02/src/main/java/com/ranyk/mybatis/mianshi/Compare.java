package com.ranyk.mybatis.mianshi;

/**
 * ClassName:Compare
 * Description:
 *
 * @author ranyi
 * @date 2020-06-02 20:01
 * Version: V1.0
 */
public interface Compare {

    /**
     * 获取整形数组中最大值
     *
     * @param nums 入参,int数组
     * @return 返回数组中的最大值
     */
    int max(int... nums);


    /**
     * 获取两个int类型数字的最大值
     *
     * @param num1 int数值一
     * @param num2 int数值二
     * @return 返回两个int数据中的最大值
     */
    int max(int num1, int num2);


    /**
     * 获取三个int类型数字的最大值
     *
     * @param num1 int数值一
     * @param num2 int数值二
     * @param num3 int数值三
     * @return 返回三个int数据中的最大值
     */
    int max(int num1, int num2, int num3);


    /**
     * 获取五个int类型数值的最大值
     *
     * @param num1 int数值一
     * @param num2 int数值二
     * @param num3 int数值三
     * @param num4 int数值四
     * @param num5 int数值五
     * @return 返回五个int数据中的最大值
     */
    int max(int num1, int num2, int num3, int num4, int num5);
}