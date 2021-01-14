package com.ranyk.mybatis.mianshi;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;

/**
 * ClassName:CompareImpl
 * Description:
 *
 * @author ranyi
 * @date 2020-06-02 20:01
 * Version: V1.0
 */
@Slf4j
public class CompareImpl implements Compare {


    /**
     * 获取整形数组中最大值
     *
     * @param nums 入参,int数组
     * @return 返回数组中的最大值
     */
    @Override
    public int max(int... nums) {
        int max = 0;
        for (int i : nums) {
            max = max(max, i);
        }
        return max;
    }


    /**
     * 获取两个int类型数字的最大值
     *
     * @param num1 int数值一
     * @param num2 int数值二
     * @return 返回两个int数据中的最大值
     */
    @Override
    public int max(int num1, int num2) {
        return Math.max(num1, num2);
    }


    /**
     * 获取三个int类型数字的最大值
     *
     * @param num1 int数值一
     * @param num2 int数值二
     * @param num3 int数值三
     * @return 返回三个int数据中的最大值
     */
    @Override
    public int max(int num1, int num2, int num3) {
        return max(max(num1, num2), num3);
    }


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
    @Override
    public int max(int num1, int num2, int num3, int num4, int num5) {
        int[] ints = {num1, num2, num3, num4, num5};
        return max(ints);
    }


    /**
     * 从身份证号中获取年纪信息
     *
     * @param idCard 身份证号
     * @return 返回年龄数值
     */
    public static int getAge(String idCard) {
        Period p = getDateFormIdCard(idCard);
        log.info("年龄 : {} 年 {} 月 {} 日", p.getYears(), p.getMonths(), p.getDays());
        return p.getYears();
    }


    /**
     * 获取身份证中出生年月日到今天的时间差
     *
     * @param idCard 身份证号
     * @return 返回时间差
     */
    public static Period getDateFormIdCard(String idCard) {
        //得到年份
        int year = getNum(idCard, 6, 4);
        // 得到月份
        int month = getNum(idCard, 10, 2);
        //得到日
        int day = getNum(idCard, 12, 2);
        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.of(year, month, day);
        return Period.between(birthDate, today);
    }


    /**
     * 获取身份证上的有效信息
     *
     * @param idCard     身份证号
     * @param startIndex 开始位置
     * @param length     截取字符长度
     * @return 返回获取到的数值
     */
    public static int getNum(String idCard, int startIndex, int length) {
        return Integer.parseInt(idCard.substring(startIndex).substring(0, length));
    }


    /**
     * main 方法
     *
     * @param args 默认参数
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String idNo="510824199612161637";
        int age = getAge(idNo);
        log.info("年纪: {}",age);
    }
}
