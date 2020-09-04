package com.ranyk.mybatis.service;

import com.ranyk.mybatis.entity.Loginuser;

import java.util.List;

/**
 * 登录用户测试(Loginuser)表服务接口
 *
 * @author makejava
 * @since 2020-06-28 18:19:48
 */
public interface LoginuserService {

    /**
     * 通过主键查询对象
     *
     * @param id
     * @return
     */
    Loginuser selectById(Integer id);

    /**
     * 通过对象查询
     *
     * @param loginuser
     * @return
     */
    List<Loginuser> selectByLoginuser(Loginuser loginuser);
}