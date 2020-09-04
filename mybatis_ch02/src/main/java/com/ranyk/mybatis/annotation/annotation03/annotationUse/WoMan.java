package com.ranyk.mybatis.annotation.annotation03.annotationUse;

import com.ranyk.mybatis.annotation.annotation03.annotationDefinition.Factory;

/**
 * ClassName:WoMan
 * Description:Personal工厂实现类二
 *
 * @author ranyi
 * @date 2020-08-07 14:52
 * Version: V1.0
 */
@Factory(id = "woMan",type = Personal.class)
public class WoMan implements Personal{
    @Override
    public String getPersonalName() {
        return "女人的姓名";
    }

    @Override
    public String getSex() {
        return "女";
    }
}
