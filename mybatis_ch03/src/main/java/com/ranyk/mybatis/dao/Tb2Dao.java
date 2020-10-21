package com.ranyk.mybatis.dao;

import com.ranyk.mybatis.entity.Tb2;
import com.ranyk.mybatis.entity.Tb23;
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
     * @param limit  查询条数
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
     *
     * @param tb23
     * @return
     */
    List<Tb2> queryAll23(Tb23 tb23);

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
     *
     * @return 返回一个null值的list集合
     */
    List<Object> queryNullFromDual();

    /**
     * 通过姓名模糊查询
     *
     * @param name 查询条件的封装对象
     * @return 返回查询结果 List 集合
     */
    List<Tb2> selectLikeByName(@Param("name") String name);

    /**
     * if 条件中 参数判定 &lt;if test="abc != null and abc=='1'.toString()" &gt;<br/>
     *     在此处传入的参数为 int 类型时, 其if 条件判断结果为false;<br/>
     *     当此处传入的参数为 char 类型时, 其if条件判断结果为false;<br/>
     *     当此处传入的参数为 String 类型时,其if条件判断结果为true;<br/>
     * if 条件中 参数判定 &lt;if test="abc != null and abc=='1'" &gt;<br/>
     *    当在此处传入的参数为 int 类型时, 其if 条件判断结果为false;<br/>
     *    当此处传入的参数为 char 类型时, 其if条件判断结果为true;<br/>
     *    当此处传入的参数为 String 类型时,其if条件判断结果为false;<br/>
     * 可以理解 abc 和 其后的参数类型一致则为 true;反之为 false;
     * 当传入参数为 boolean 类型是
     *
     * @param name
     * @return
     */
    List<Tb2> selectLikeByName2(@Param("name") String name);

    /**
     *  if 条件中 参数判定 &lt;if test="abc != null and abc != ''" &gt; <br/>
     *    当判断条件为 abc != '' 或 abc == '' 会对 ‘’ 根据传入参数类型进行对应默认值转换<br/>
     *    当判断条件为 abc != 'a' 情况时, 会对传入的参数进行对应的类型转换, 如: 传入的参数为 int 类型,值为 0 在对比时会将 a 转换成对应的 Unicode 编码值进行对比 <br/>
     *
     * @param name
     * @return
     */
    List<Tb2> selectLikeByName3(@Param("name") int name);
}