package com.ranyk.mybatis.util;

/**
 * CLASS_NAME: IntegerOperate<br/>
 * Description: 整数操作工具类<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
public class IntegerOperate {


    /**
     * 判断一个数是否是奇数
     *
     * @param number 需要判断的整数
     * @return 如果是奇数返回 true;反之返回 false;
     */
    public static boolean isOddNumber(Integer number){
        return number%2 == 1;
    }


    /**
     * 判断一个数是否是偶数
     *
     * @param number 需要判断的整数
     * @return 如果是偶数返回 true;反之返回false;
     */
    public static boolean isEven(Integer number){
        return number%2 ==0;
    }

}
