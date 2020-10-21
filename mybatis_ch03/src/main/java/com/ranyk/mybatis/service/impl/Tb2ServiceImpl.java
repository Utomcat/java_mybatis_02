package com.ranyk.mybatis.service.impl;

import com.ranyk.mybatis.dao.Tb2Dao;
import com.ranyk.mybatis.entity.Tb2;
import com.ranyk.mybatis.entity.Tb23;
import com.ranyk.mybatis.service.Tb2Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Tb2)表服务实现类
 *
 * @author makejava
 * @since 2020-05-24 21:31:48
 */
@Service("tbService")
public class Tb2ServiceImpl implements Tb2Service {
    @Resource
    private Tb2Dao tb2Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Tb2 queryById(String id) {
        return tb2Dao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Tb2> queryAllByLimit(int offset, int limit) {
        return tb2Dao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询满足某项条件的List 集合
     * @param tb2 条件封装对象
     * @return 返回查询结果 List 集合
     */
    @Override
    public List<Tb2> queryAllByTb2(Tb2 tb2) {
        return tb2Dao.queryAll(tb2);
    }

    @Override
    public List<Tb2> queryAllByTb23(Tb23 tb23) {
        return tb2Dao.queryAll23(tb23);
    }

    /**
     * 新增数据
     *
     * @param tb2 实例对象
     * @return 实例对象
     */
    @Override
    public Tb2 insert(Tb2 tb2) {
        int insert = tb2Dao.insert(tb2);
        if (insert <= 0) {
            return null;
        }
        return tb2;
    }

    /**
     * 修改数据
     *
     * @param tb2 实例对象
     * @return 实例对象
     */
    @Override
    public Tb2 update(Tb2 tb2) {
        int update = tb2Dao.update(tb2);

        if (update <= 0) {
            return null;
        }

        return queryById(tb2.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return tb2Dao.deleteById(id) > 0;
    }

    /**
     * 空值查询
     *
     * @return 返回一个 NULL 的 List 集合
     */
    @Override
    public List<Object> queryNullFromDual() {
        return tb2Dao.queryNullFromDual();
    }

    /**
     * 通过姓名模糊查询
     *
     * @param tb2 需要查询的参数对象
     * @return 查询结果的 List 集合
     */
    @Override
    public List<Tb2> fuzzyQueryByName(Tb2 tb2) {
        /*return tb2Dao.selectLikeByName(tb2.getName());*/

        return tb2Dao.selectLikeByName3((int)'a');
    }



}