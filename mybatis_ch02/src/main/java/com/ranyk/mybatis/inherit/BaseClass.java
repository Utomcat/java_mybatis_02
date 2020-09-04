package com.ranyk.mybatis.inherit;

/**
 * ClassName:BaseClass
 * Description:继承的基类
 *
 * @author ranyi
 * @date 2020-07-28 10:16
 * Version: V1.0
 */
public class BaseClass {

    private String success = "SUCCESS";

    private BaseClass(){}

    public BaseClass(String str){
        this.success = str;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public BaseClass getBase(){
        return new BaseClass();
    }

}
