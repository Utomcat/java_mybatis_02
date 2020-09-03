package com.ranyk.mybatis_ch02.annotation.annotation03.annotationUse;

import com.ranyk.mybatis_ch02.annotation.annotation03.annotationDefinition.Factory;

/**
 * ClassName:CalPizza
 * Description:由工厂生产的产品二 CalzonePizza
 *
 * @author ranyi
 * @date 2020-08-07 10:07
 * Version: V1.0
 */
@Factory(
        type = Meal.class,
        id = "claPizza"
)
public class CalPizza implements Meal{

    @Override
    public float getPrice() {
        return 10f;
    }

    @Override
    public String getName() {
        return "Calzone Pizza";
    }

    public void selfMethod(){
        System.out.println("自定义的其他方法");
    }
}
