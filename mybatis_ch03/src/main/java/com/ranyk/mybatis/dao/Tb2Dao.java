package com.ranyk.mybatis.dao;

import com.ranyk.mybatis.entity.Tb2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Tb2)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-24 21:31:48
 */
@Mapper
@Repository
public interface Tb2Dao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Tb2 queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Tb2> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tb2 实例对象
     * @return 对象列表
     */
    List<Tb2> queryAll(Tb2 tb2);

    /**
     * 新增数据
     *
     * @param tb2 实例对象
     * @return 影响行数
     */
    int insert(Tb2 tb2);

    /**
     * 修改数据
     *
     * @param tb2 实例对象
     * @return 影响行数
     */
    int update(Tb2 tb2);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);


    /**
     * 空值查询
     * @return 返回一个null值的list集合
     */
    List<Object> queryNullFromDual();
}