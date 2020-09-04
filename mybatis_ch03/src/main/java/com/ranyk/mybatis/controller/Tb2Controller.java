package com.ranyk.mybatis.controller;

import com.ranyk.mybatis.entity.Tb2;
import com.ranyk.mybatis.service.Tb2Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (Tb2)表控制层
 *
 * @author makejava
 * @since 2020-05-24 21:31:48
 */
@RestController
@RequestMapping("tb2")
public class Tb2Controller {
    /**
     * 服务对象
     */
    @Resource
    private Tb2Service tbService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne/{id}")
    public Tb2 selectOne(@PathVariable("id") String id) {
        return tbService.queryById(id);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param map 前台传入的字符串是json格式的字符串，后台使用json格式字符串的方式进行解析后存放数据的对象，此对象可以是Map、实体类、JSONObject(实际上是Map)，List<Map<String,Object>>(待确定)
     * @return 单条数据
     */
    @PostMapping("selectOne")
    public Tb2 selectOne(@RequestBody Map map) {
        return tbService.queryById(String.valueOf(map.get("id")));
    }



    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne2")
    public Tb2 selectOne2(@RequestParam("id") String id) {
        return tbService.queryById(id);
    }

}