package com.ranyk.mybatis.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:MyException
 * Description:自定义异常
 *
 * @author ranyi
 * @date 2020-08-07 16:26
 * Version: V1.0
 */
@Slf4j
@Getter
@Setter
public class MyException extends Exception{

    private String message;
    private Integer code = 500;

    /**
     * 无参构造方法
     */
    public MyException() {
        super();
    }

    /**
     * 含异常信息的构造方法
     * @param message 捕获的异常信息
     */
    public MyException(String message){
        super(message);
        this.message = message;
    }

    /**
     * 含异常信息和状态码的构造方法
     * @param message 捕获的异常信息
     * @param code 状态码
     */
    public MyException(String message,Integer code){
        super(message);
        this.message = message;
        this.code = code;
    }






}
