package com.ranyk.mybatis.inherit;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:BaseClass
 * Description:继承的基类
 *
 * @author ranyi
 * @date 2020-07-28 10:16
 * Version: V1.0
 */
@Data
@Slf4j
public class BaseClass {

    private String success = "SUCCESS";

    public BaseClass(){
        log.info("调用 BaseClass 类的无参构造方法!");
    }

    public BaseClass(String str){
        log.info("调用 BaseClass 类的有参构造方法!");
        this.success = str;
    }

    public BaseClass getBase(){
        return new BaseClass(success);
    }

}
