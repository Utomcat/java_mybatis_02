package com.ranyk.mybatis_ch02.mianshi;

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
public class CompareImpl implements Compare{

    @Override
    public int max(int[] nums) {
        int max = 0;
        for (int i :nums) {
            max = max(max, i);
        }
        return max;
    }

    @Override
    public int max(int num1, int num2) {
        return (num1 >= num2) ? num1 : num2;
    }

    @Override
    public int max(int num1, int num2, int num3) {
        int max = max(max(num1, num2),num3);
        return max;
    }

    @Override
    public int max(int num1, int num2, int num3, int num4, int num5) {
        int[] ints = {num1,num2,num3,num4,num5};
        return max(ints);
    }


    public static int getAge(String idCard){

        //String birthStr = idCard.substring(6, 14);
        //DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        //LocalDate birth = LocalDate.parse(birthStr,formatter);

        int year =Integer.valueOf(idCard.substring(6).substring(0, 4)) ;// 得到年份
        int month =Integer.valueOf(idCard.substring(10).substring(0, 2));// 得到月份
        int day=Integer.valueOf(idCard.substring(12).substring(0,2));//得到日
        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.of(year, month, day);
        Period p = Period.between(birthDate, today);
        //System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());


        return  p.getYears();
    }


    public static int getAge1(String idCard){

        /**
         * 得到年份
         */
        int year =Integer.valueOf(idCard.substring(6).substring(0, 4)) ;
        /**
         * 得到月份
         */
        int month =Integer.valueOf(idCard.substring(10).substring(0, 2));
        /**
         * 得到日
         */
        int day=Integer.valueOf(idCard.substring(12).substring(0,2));//
        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.of(year, month, day);
        Period p = Period.between(birthDate, today);


        return  p.getYears();
    }

    public static void main(String[] args) {
        System.out.println(getAge("510824199612161637"));
        // TODO Auto-generated method stub

        //String idNo="510824199612161637";


        //int year =Integer.valueOf(idNo.substring(6).substring(0, 4)) ;// 得到年份
        //int month =Integer.valueOf(idNo.substring(10).substring(0, 2));// 得到月份
        //int day=Integer.valueOf(idNo.substring(12).substring(0,2));//得到日

        //LocalDate today = LocalDate.now();
        //System.out.println("Today : " + today);
        //LocalDate birthDate = LocalDate.of(year, month, day);
        //System.out.println("BirthDate : " + birthDate);
        //Period p = Period.between(birthDate, today);
        //System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());
    }
}
