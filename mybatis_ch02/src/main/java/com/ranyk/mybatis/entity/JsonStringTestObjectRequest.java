package com.ranyk.mybatis.entity;

import lombok.*;

import java.io.Serializable;

/**
 * CLASS_NAME: JsonStringTestObjectRequest<br/>
 * Description: json字符串转换测试的前台传入参数对象<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class JsonStringTestObjectRequest implements Serializable {

    private String name;
    private Integer age;
    private String sex;
    private String params;

}
