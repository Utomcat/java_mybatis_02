package com.ranyk.mybatis.annotation.annotation03.annotationUse;

import com.ranyk.mybatis.annotation.annotation03.annotationDefinition.Factory;

/**
 * ClassName:MarPizza
 * Description:有工厂生产的产品一 MarPizza
 *
 * @author ranyi
 * @date 2020-08-07 9:51
 * Version: V1.0
 */
@Factory(
        type =Meal.class,
        id = "marPizza"
)
public class MarPizza implements Meal{

    @Override
    public float getPrice() {
        return 6f;
    }

    @Override
    public String getName() {
        return "Margherita Pizza";
    }
}
