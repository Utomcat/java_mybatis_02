package com.ranyk.mybatis_ch02.annotation.annotation03.annotationUse;

import com.ranyk.mybatis_ch02.annotation.annotation03.annotationDefinition.Factory;

/**
 * ClassName:Man
 * Description:Personal工厂类实现类一
 *
 * @author ranyi
 * @date 2020-08-07 14:50
 * Version: V1.0
 */
@Factory(id = "man",type = Personal.class)
public class Man implements Personal{
    @Override
    public String getPersonalName() {
        return "男人的姓名";
    }

    @Override
    public String getSex() {
        return "男";
    }
}
