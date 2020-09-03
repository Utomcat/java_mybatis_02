package com.ranyk.mybatis_ch02.annotation.annotation03.annotationTest;

import com.ranyk.mybatis_ch02.annotation.annotation03.annotationProcessor.BeanFactory;
import com.ranyk.mybatis_ch02.annotation.annotation03.annotationProcessor.impl.AnnApplicationContext;
import com.ranyk.mybatis_ch02.annotation.annotation03.annotationUse.CalPizza;
import com.ranyk.mybatis_ch02.annotation.annotation03.annotationUse.Meal;

/**
 * ClassName:MealAnnotationTest
 * Description:注解测试一
 *
 * @author ranyi
 * @date 2020-08-07 10:13
 * Version: V1.0
 */
public class MealAnnotationTest {

    public static void main(String[] args) {
        try {
            BeanFactory factoryProcessor = new AnnApplicationContext("com.ranyk.mybatis_ch02.annotation.annotation03");
            CalPizza meal = (CalPizza)factoryProcessor.getBean("Clapizza", Meal.class);
            System.out.println(meal.getPrice());
            meal.selfMethod();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
