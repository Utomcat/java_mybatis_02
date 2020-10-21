package com.ranyk.mybatis.service;

import com.ranyk.mybatis.entity.Tb2;
import com.ranyk.mybatis.entity.Tb23;

import java.util.List;

/**
 * (Tb2)表服务接口
 *
 * @author makejava
 * @since 2020-05-24 21:31:48
 */
public interface Tb2Service {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Tb2 queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Tb2> queryAllByLimit(int offset, int limit);


    /**
     * 查询满足某项条件的List 集合
     *
     * @param tb2 条件封装对象
     * @return 返回 List 集合
     */
    List<Tb2> queryAllByTb2(Tb2 tb2);


    List<Tb2> queryAllByTb23(Tb23 tb23);

    /**
     * 新增数据
     *
     * @param tb2 实例对象
     * @return 实例对象
     */
    Tb2 insert(Tb2 tb2);

    /**
     * 修改数据
     *
     * @param tb2 实例对象
     * @return 实例对象
     */
    Tb2 update(Tb2 tb2);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);


    /**
     * 空值查询
     *
     * @return 返回一个Null 的List 集合
     */
    List<Object> queryNullFromDual();

    /**
     * 通过姓名模糊查询
     *
     * @param tb2 需要查询的参数对象
     * @return 返回查询的 List 集合
     */
    List<Tb2> fuzzyQueryByName(Tb2 tb2);

}