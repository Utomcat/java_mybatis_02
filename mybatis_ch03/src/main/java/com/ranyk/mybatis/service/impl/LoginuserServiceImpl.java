package com.ranyk.mybatis.service.impl;

import com.ranyk.mybatis.dao.LoginuserDao;
import com.ranyk.mybatis.entity.Loginuser;
import com.ranyk.mybatis.service.LoginuserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录用户测试(Loginuser)表服务实现类
 *
 * @author makejava
 * @since 2020-06-28 18:19:50
 */
@Service("loginService")
public class LoginuserServiceImpl implements LoginuserService {
    @Resource
    private LoginuserDao loginuserDao;


    @Override
    public Loginuser selectById(Integer id) {
        return loginuserDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Loginuser> selectByLoginuser(Loginuser loginuser) {
        return loginuserDao.selectByLoginuser(loginuser);
    }
}