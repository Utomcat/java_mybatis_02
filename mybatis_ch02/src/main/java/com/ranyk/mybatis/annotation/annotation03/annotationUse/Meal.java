package com.ranyk.mybatis.annotation.annotation03.annotationUse;

/**
 * ClassName:Meal
 * Description:工厂接口
 *
 * @author ranyi
 * @date 2020-08-07 9:50
 * Version: V1.0
 */
public interface Meal {

    /**
     * 得到价格
     * @return 返回价格
     */
    float getPrice();

    /**
     * 得到名称
     * @return 返回名称
     */
    String getName();

}
