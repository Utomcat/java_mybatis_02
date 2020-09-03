package com.ranyk.mybatis_ch01.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * (Tb2)实体类
 *
 * @author makejava
 * @since 2020-05-24 21:31:45
 */

@Data
@ToString
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Tb2 {
    private static final long serialVersionUID = 571796332287869377L;
    /**
    * 主键
    */
    private String id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 课程
    */
    private String course;
    /**
    * 成绩
    */
    private String score;


}