package com.ranyk.mybatis_ch02.oop.pojo;

import lombok.*;

/**
 * ClassName:OopTest
 * Description:对象测试一
 *
 * @author ranyi
 * @date 2020-09-03 10:05
 * Version: V1.0
 */
public class OopTest {


    public static void main(String[] args) {
        Man man = null;
        System.out.println(man.getName());
    }


}

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
class Man{
    private String name;
    private Integer age;
}
