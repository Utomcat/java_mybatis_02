package com.ranyk.mybatis.base;

/**
 * CLASS_NAME: ConstantBase<br/>
 * Description: 常量基本接口<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
public interface ConstantBase<T>{


    /**
     * 获取常量值方法
     *
     * @return 返回获取到的常量值
     */
    T getValue();

}
