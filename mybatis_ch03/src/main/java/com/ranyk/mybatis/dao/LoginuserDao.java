package com.ranyk.mybatis.dao;

import com.ranyk.mybatis.entity.Loginuser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ranyi
 */
@Mapper
@Repository
public interface LoginuserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Loginuser record);

    int insertSelective(Loginuser record);

    Loginuser selectByPrimaryKey(Integer id);

    List<Loginuser> selectByLoginuser(Loginuser loginuser);

    int updateByPrimaryKeySelective(Loginuser record);

    int updateByPrimaryKey(Loginuser record);

}