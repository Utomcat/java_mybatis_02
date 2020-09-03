package com.ranyk.mybatis_ch02.annotation.annotation03.annotationUse;

/**
 * ClassName:Personal
 * Description:工厂接口二
 *
 * @author ranyi
 * @date 2020-08-07 14:49
 * Version: V1.0
 */
public interface Personal {

    /**
     * 得到姓名
     * @return 返回姓名字符串
     */
    String getPersonalName();

    /**
     * 得到性别
     * @return 返航时性别
     */
    String getSex();

}
