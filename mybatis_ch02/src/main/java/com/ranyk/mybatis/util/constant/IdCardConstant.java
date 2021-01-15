package com.ranyk.mybatis.util.constant;

import com.ranyk.mybatis.base.ConstantBase;
import lombok.Getter;
import lombok.Setter;

/**
 * CLASS_NAME: IdCardConstant<br/>
 * Description: 身份证常量枚举类<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
public enum IdCardConstant implements ConstantBase<String> {

    /**
     * 15位身份证号长度
     */
    FIFTEEN_ID_LENGTH(" 15位身份证号长度", 0, 0, 15),
    /**
     * 18位身份证号长度
     */
    EIGHTEEN_ID_LENGTH(" 15位身份证号长度", 0, 0, 18),
    /**
     * 15位身份证号正则表达式
     */
    FIFTEEN_DIGIT_ID_NUMBER_REGULAR_EXPRESSION("^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$",0,0,0),
    /**
     * 18位身份证号正则表达式
     */
    EIGHTEEN_DIGIT_ID_NUMBER_REGULAR_EXPRESSION("^[1-9]\\d{5}(18|19|(2[0-3]))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9X]$",0,0,0),
    /**
     * 15位身份证号中出生地编码(六位)
     */
    FIFTEEN_DIGIT_ID_NUMBER_BIRTH_PLACE_CODE("出生地编码", 6, 0, 0),
    /**
     * 15位身份证号中出生年份(两位)
     */
    FIFTEEN_DIGIT_ID_NUMBER_YEAR_OF_BIRTH("出生年份(两位)", 2, 6, 0),
    /**
     * 15位身份证号中出生月份(两位)
     */
    FIFTEEN_DIGIT_ID_NUMBER_MONTH_OF_BIRTH("出生月份", 2, 8, 0),
    /**
     * 15位身份证号中出生日期(两位)
     */
    FIFTEEN_DIGIT_ID_NUMBER_DATE_OF_BIRTH("出生日期", 2, 10, 0),
    /**
     * 15位身份证号中出生顺序编号(两位)
     */
    FIFTEEN_DIGIT_ID_NUMBER_BIRTH_ORDER_NUMBER("出生顺序编号", 2, 12, 0),
    /**
     * 15位身份证号中性别标号(奇数男性,偶数女性,一位)
     */
    FIFTEEN_DIGIT_ID_NUMBER_GENDER_MARKING("性别标号(奇数男性,偶数女性)", 1, 14, 0),
    /**
     * 18位身份证号中出生地编码(六位)
     */
    EIGHTEEN_DIGIT_ID_NUMBER_BIRTH_PLACE_CODE("出生地编码", 6, 0, 0),
    /**
     * 18位身份证号中出生年份(四位)
     */
    EIGHTEEN_DIGIT_ID_NUMBER_YEAR_OF_BIRTH("出生年份(四位)", 4, 6, 0),
    /**
     * 18位身份证号中出生月份(两位)
     */
    EIGHTEEN_DIGIT_ID_NUMBER_MONTH_OF_BIRTH("出生月份", 2, 10, 0),
    /**
     * 18位身份证号中出生日期(两位)
     */
    EIGHTEEN_DIGIT_ID_NUMBER_DATE_OF_BIRTH("出生日期", 2, 12, 0),
    /**
     * 18位身份证号中出生顺序编号(两位)
     */
    EIGHTEEN_DIGIT_ID_NUMBER_BIRTH_ORDER_NUMBER("出生顺序编号", 2, 14, 0),
    /**
     * 18位身份证号中性别标号(一位)
     */
    EIGHTEEN_DIGIT_ID_NUMBER_GENDER_MARKING("性别标号(奇数男性,偶数女性)", 1, 16, 0),
    /**
     * 18位身份证号中校验码(一位)
     */
    EIGHTEEN_DIGIT_ID_NUMBER_CHECK_CODE("校验码", 1, 17, 0);


    /**
     * 当前的身份证中数值代表的含义
     */
    @Setter
    private String value;
    /**
     * 需要获取的值长度
     */
    @Setter
    @Getter
    private Integer length;
    /**
     * 开始截取位置索引
     */
    @Setter
    @Getter
    private Integer startIndex;
    /**
     * 数值
     */
    @Setter
    @Getter
    private Integer numberValue;


    /**
     * 构造函数
     *
     * @param value       设置当前数值代表的含义
     * @param length      设置数值长度
     * @param startIndex  设置截取位置索引
     * @param numberValue 设置数值
     */
    IdCardConstant(String value, Integer length, Integer startIndex, Integer numberValue) {
        this.value = value;
        this.length = length;
        this.startIndex = startIndex;
        this.numberValue = numberValue;
    }


    /**
     * 获取常量值方法
     *
     * @return 返回获取到的常量值
     */
    @Override
    public String getValue() {
        return this.value;
    }
}
