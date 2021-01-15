package com.ranyk.mybatis.util.vo;

import lombok.*;

/**
 * CLASS_NAME: PersonInfo<br/>
 * Description: 个人信息封装实体对象类<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfo {

    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年纪
     */
    private Integer age;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 出生地编码
     */
    private Integer birthPlaceCode;
    /**
     * 出生年份
     */
    private Integer yearOfBirth;
    /**
     * 出生月份
     */
    private Integer monthOfBirth;
    /**
     * 出生日期
     */
    private Integer dateOfBirth;
    /**
     * 出生顺序编号
     */
    private Integer birthOrderNumber;
    /**
     * 性别标号
     */
    private Integer genderMarking;
    /**
     * 校验码
     */
    private Integer checkCode;
    /**
     * 户口地址
     */
    private String accountAddress;


    /**
     * 含名字和身份证号的构造函数
     *
     * @param name   姓名
     * @param idCard 身份证号
     */
    public PersonInfo(String name, String idCard) {
        this.name = name;
        this.idCard = idCard;
    }
}
