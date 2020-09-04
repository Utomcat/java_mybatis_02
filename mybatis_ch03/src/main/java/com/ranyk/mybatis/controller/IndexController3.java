package com.ranyk.mybatis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:IndexController
 * Description:测试请求一
 *
 * @author ranyi
 * @date 2020-05-19 0:16
 * Version: V1.0
 */
@RestController
@RequestMapping("/index_3/")
public class IndexController3 {


    @GetMapping("index3")
    public String index1(){
        return "hello world 3!";
    }

}
