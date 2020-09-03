package com.ranyk.mybatis_ch02.datatype.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName:StringTest
 * Description:String 数据类型学习类
 *
 * @author ranyi
 * @date 2020-08-10 14:38
 * Version: V1.0
 */
public class StringTest {


    private final static String REGEX = "[0-9]*";

    public static void main(String[] args) {
        StringTest stringTest = new StringTest();
        try {
            stringTest.checkNatuId("aa222","111");
            System.out.println("测试完成================");
        } catch (Exception e) {
            e.printStackTrace();
        }



    }



    /**
     * 检查开始楼栋和终止楼栋是否为数字
     *
     * @param startNatuId 开始楼栋
     * @param endNatuId   结束楼栋
     */
    private void checkNatuId(String startNatuId, String endNatuId) throws Exception{
        if (!isNumber(startNatuId) || !isNumber(endNatuId)) {
            throw new Exception("输入的开始楼栋编号和终止楼栋编号中存在非数字的值，请核查！");
        }
    }

    /**
     * 使用正则来判断是否是满足输入的字符串是数字
     *
     * @param str 需要比对的字符串
     * @return 如果是数字则返回 true; 否则返回 false;
     */
    private boolean isNumber(String str) {

        Pattern pattern = Pattern.compile(REGEX);
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

}
