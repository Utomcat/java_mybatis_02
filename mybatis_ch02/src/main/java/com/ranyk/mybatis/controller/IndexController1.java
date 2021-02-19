package com.ranyk.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ranyk.mybatis.entity.JsonStringTestObject;
import com.ranyk.mybatis.entity.JsonStringTestObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:IndexController
 * Description:测试请求一
 *
 * @author ranyi
 * @date 2020-05-19 0:16
 * Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/index_2/")
public class IndexController1 {


    @GetMapping("index2")
    public String index1(){
        return "hello world 12 !";
    }


    @GetMapping("paramTest01")
    public String index2(@RequestBody Object param1){
        log.info("param1 ==> {}",param1);
        String param = JSON.toJSONString(JSONObject.parseObject(JSON.toJSONString(param1)).get("param1"));
        log.info("param ==> {}", param);
        JSONArray list = JSON.parseArray(param);
        log.info("list ==> {}",list);
        List<JsonStringTestObject> result = JSONObject.parseArray(param, JsonStringTestObject.class);
        log.info("result ==> {}",result);
        return "请求成功";
    }

    @PostMapping("paramTest02")
    public String index3(JsonStringTestObjectRequest paramRequest){
        log.info("paramRequest ==> {}",paramRequest);
        String params = paramRequest.getParams();
        log.info("params ==> {}", params);
        JSONArray list = JSON.parseArray(params);
        log.info("list ==> {}",list);
        List<JsonStringTestObject> result = JSONObject.parseArray(params, JsonStringTestObject.class);
        log.info("result ==> {}",result);
        return "请求成功";
    }


}
