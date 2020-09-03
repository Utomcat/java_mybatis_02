package com.ranyk.mybatis_ch02.variable.test;

/**
 * ClassName:Type
 * Description:
 *
 * @author ranyi
 * @date 2020-06-23 13:31
 * Version: V1.0
 */
public class Type {

    private String name ;
    private int id;

    public Type(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
