package com.ranyk.mybatis.util;

import com.ranyk.mybatis.util.constant.IdCardConstant;
import com.ranyk.mybatis.util.vo.PersonInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.regex.Pattern;

/**
 * ClassName:StringOperate
 * Description:对字符串的操作
 *
 * @author ranyi
 * @date 2020-05-24 17:21
 * Version: V1.0
 */
@Slf4j
public class StringOperate {


    /**
     * 统计字符串中的某个字符出现的次数：
     * 处理逻辑：将字符串转成字符数组，遍历对应的字符数，某个字符出现一次则计数一次
     *
     * @param originalCharacter 需要统计的字符串
     * @param str               需要查找的字符
     * @return 返回计数的值
     */
    public static int statisticsStr1(String originalCharacter, Character str) {
        char[] chars = originalCharacter.toCharArray();
        int count = 0;
        for (char ch : chars) {
            if (str.equals(ch)) {
                count++;
            }
        }
        return count;
    }


    /**
     * 统计指定字符串中各个字符的数量
     *
     * @param str 需要进行统计的字符串
     * @return 返回存放各个字符数量的Map集合
     */
    public static Map<String, Integer> statisticsStr2(String str) {

        Map<String, Integer> count = new HashMap<>(16);
        //将字符串分割 每一个字符单独作为一个字符串放进字符串数组中
        String[] mySirs = str.split("");
        //循环此字符串数组，将其对应的每个字符放进Map的key中，然后每个字符的数量统计放进Map的value中
        for (String mySir : mySirs) {
            int totalNum = 1;
            if (count.containsKey(mySir)) {
                totalNum = count.get(mySir) + 1;
            }
            count.put(mySir, totalNum);
        }

        return count;
    }


    /**
     * 判断字符串是否为空
     *
     * @param str 需要判定的字符串
     * @return 返回判定结构
     */
    public static Boolean judgeStringIsNull(String str) {
        return StringUtils.isEmpty(str) && !StringUtils.hasLength(str) && !StringUtils.hasText(str);
    }


    /**
     * 字符串替换
     *
     * @param originalStr 原始字符串
     * @param targetStr   需要替换的目标字符串
     * @param replaceStr  替换字符串
     * @return 返回替换结果
     * @throws Exception 抛出异常: 不存在需要替换的字符串异常
     */
    public static String replaceStr(String originalStr, String targetStr, String replaceStr) throws Exception {
        if (!originalStr.contains(targetStr)) {
            throw new Exception("原始字符串不包含需要更换的子字符串！");
        }

        return originalStr.replace(targetStr, replaceStr);
    }


    /**
     * 从身份证中获取个人信息,除姓名外
     *
     * @param idCard 身份证号
     * @return 返回获取到的个人信息
     */
    public static PersonInfo getPersonInfoByIdCard(String idCard) {
        //1. 获取传入的身份证号的长度,用于之后判断需要怎么截取其值
        int length = idCard.length();
        //2. 需要验证传入的身份证是否有效,包括身份证号的长度是否合法
        if (!verifyValid(idCard,length)){
            throw new NumberFormatException("无效的身份证号,请核查!");
        }
        //3. 获取身份证中的信息
        return getPersonInfo(idCard, length);
    }


    /**
     * 验证有效性
     *
     * @param idCard 需要验证的身份证号
     * @return 有效的返回true;反之返回false;
     */
    public static boolean verifyValid(String idCard, int length) {
        //1. 判断身份证长度是否正确
        if (length != IdCardConstant.FIFTEEN_ID_LENGTH.getNumberValue() && length != IdCardConstant.EIGHTEEN_ID_LENGTH.getNumberValue()){
            throw new NumberFormatException("传入的身份证的长度不对,请核查其长度!");
        }
        //TODO 如果需要对传入的身份证进行数值验证,需要的对其进行身份证计算验证

        //2. 判断身份证的格式是否正确,通过正则进行匹配
        return Pattern.matches(
                (length == IdCardConstant.FIFTEEN_ID_LENGTH.getNumberValue()?
                        IdCardConstant.FIFTEEN_DIGIT_ID_NUMBER_REGULAR_EXPRESSION.getValue():
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_REGULAR_EXPRESSION.getValue()),
                idCard);
    }


    /**
     * 通过身份证和身份证的长度获取该身份证中的个人信息
     *
     * @param idCard       身份证号
     * @param idCardLength 身份证号码长度
     * @return 返回获取到的个人信息封装对象
     */
    private static PersonInfo getPersonInfo(String idCard, Integer idCardLength) {

        int birthPlaceCode;
        int yearOfBirth;
        int monthOfBirth;
        int dateOfBirth;
        int birthOrderNumber;
        int genderMarking;
        int checkCode;

        switch (idCardLength) {
            case 15:
                birthPlaceCode = Integer.parseInt(getSubStr(idCard,
                        IdCardConstant.FIFTEEN_DIGIT_ID_NUMBER_BIRTH_PLACE_CODE.getStartIndex(),
                        IdCardConstant.FIFTEEN_DIGIT_ID_NUMBER_BIRTH_PLACE_CODE.getLength()));
                yearOfBirth = Integer.parseInt(getSubStr(idCard,
                        IdCardConstant.FIFTEEN_DIGIT_ID_NUMBER_YEAR_OF_BIRTH.getStartIndex(),
                        IdCardConstant.FIFTEEN_DIGIT_ID_NUMBER_YEAR_OF_BIRTH.getLength()));
                monthOfBirth = Integer.parseInt(getSubStr(idCard,
                        IdCardConstant.FIFTEEN_DIGIT_ID_NUMBER_MONTH_OF_BIRTH.getStartIndex(),
                        IdCardConstant.FIFTEEN_DIGIT_ID_NUMBER_MONTH_OF_BIRTH.getLength()));
                dateOfBirth = Integer.parseInt(getSubStr(idCard,
                        IdCardConstant.FIFTEEN_DIGIT_ID_NUMBER_DATE_OF_BIRTH.getStartIndex(),
                        IdCardConstant.FIFTEEN_DIGIT_ID_NUMBER_DATE_OF_BIRTH.getLength()));
                birthOrderNumber = Integer.parseInt(getSubStr(idCard,
                        IdCardConstant.FIFTEEN_DIGIT_ID_NUMBER_BIRTH_ORDER_NUMBER.getStartIndex(),
                        IdCardConstant.FIFTEEN_DIGIT_ID_NUMBER_BIRTH_ORDER_NUMBER.getLength()));
                genderMarking = Integer.parseInt(getSubStr(idCard,
                        IdCardConstant.FIFTEEN_DIGIT_ID_NUMBER_GENDER_MARKING.getStartIndex(),
                        IdCardConstant.FIFTEEN_DIGIT_ID_NUMBER_GENDER_MARKING.getLength()));
                checkCode = 0;
                //TODO 此次还需要对户籍地址的取值
                break;
            case 18:
                birthPlaceCode = Integer.parseInt(getSubStr(idCard,
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_BIRTH_PLACE_CODE.getStartIndex(),
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_BIRTH_PLACE_CODE.getLength()));
                yearOfBirth = Integer.parseInt(getSubStr(idCard,
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_YEAR_OF_BIRTH.getStartIndex(),
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_YEAR_OF_BIRTH.getLength()));
                monthOfBirth = Integer.parseInt(getSubStr(idCard,
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_MONTH_OF_BIRTH.getStartIndex(),
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_MONTH_OF_BIRTH.getLength()));
                dateOfBirth = Integer.parseInt(getSubStr(idCard,
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_DATE_OF_BIRTH.getStartIndex(),
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_DATE_OF_BIRTH.getLength()));
                birthOrderNumber = Integer.parseInt(getSubStr(idCard,
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_BIRTH_ORDER_NUMBER.getStartIndex(),
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_BIRTH_ORDER_NUMBER.getLength()));
                genderMarking = Integer.parseInt(getSubStr(idCard,
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_GENDER_MARKING.getStartIndex(),
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_GENDER_MARKING.getLength()));
                checkCode = Integer.parseInt(getSubStr(idCard,
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_CHECK_CODE.getStartIndex(),
                        IdCardConstant.EIGHTEEN_DIGIT_ID_NUMBER_CHECK_CODE.getLength()));
                //TODO 此次还需要对户籍地址的取值
                break;
            default:
                throw new NumberFormatException("未知长度的身份证号,请核查!");
        }

        return new PersonInfo(null,
                (IntegerOperate.isOddNumber(genderMarking) ? "男" : (IntegerOperate.isOddNumber(genderMarking) ? "女" : "未知")),
                getAge(yearOfBirth, monthOfBirth, dateOfBirth),
                idCard,
                birthPlaceCode,
                yearOfBirth,
                monthOfBirth,
                dateOfBirth,
                birthOrderNumber,
                genderMarking,
                checkCode,
                null);
    }


    /**
     * 通过传入年月日,获取当前时间距离指定日期间的年份时间差
     *
     * @param year  年份
     * @param month 月份
     * @param day   日期
     * @return 返回当前时间距离指定日期的年份时间差
     */
    public static Integer getAge(Integer year, Integer month, Integer day) {
        if (year < 100) {
            year = Integer.parseInt("19" + year);
        }
        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.of(year, month, day);
        return Period.between(birthDate, today).getYears();
    }


    /**
     * 获取指定字符串从指定位置开始,截取指定长度的字符
     *
     * @param str        原字符串
     * @param startIndex 截取的开始位置
     * @param length     截取的长度
     * @return 返回截取后的结果字符串
     */
    public static String getSubStr(String str, int startIndex, int length) {
        return str.substring(startIndex).substring(0, length);
    }


}
