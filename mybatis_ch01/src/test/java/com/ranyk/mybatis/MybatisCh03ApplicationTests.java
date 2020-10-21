package com.ranyk.mybatis;

import com.ranyk.mybatis.entity.Tb2;
import com.ranyk.mybatis.entity.Tb23;
import com.ranyk.mybatis.service.Tb2Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:MainTest1
 * Description:测试类一
 *
 * @author ranyi
 * @date 2020-09-03 10:12
 * Version: V1.0
 */
@Slf4j
@SpringBootTest
public class MybatisCh03ApplicationTests {

    @Autowired
    private Tb2Service tbService;


    /**
     * 验证 MyBatis 测试查询结果不存在时，对应的实体对象是否为 null
     * 结果 实体对象为 null 且对实体对象进行的属性值的获取会出现 空指针
     */
    @Test
    public void method0() {
        Tb2 tb2 = tbService.queryById("8");
        System.out.println(tb2.toString());
    }


    /**
     * 空值处理验证一
     */
    @Test
    public void method1() {

        for (int i = 0; i < 12; i++) {

            Tb2 tb2 = tbService.queryById(String.valueOf(i));

            if (null == tb2) {
                log.error("未查询到数据");
                continue;
            }

            log.error(tb2.toString());
        }


    }


    /**
     * 空值处理验证二
     */
    @Test
    public void method2() {
        List<Object> objects = tbService.queryNullFromDual();
        for (Object object : objects) {
            System.out.println(object);
        }
    }


    /**
     * 验证 try catch 之后,在catch中不进行异常的抛出,仅输出错误行时, 程序是否能够继续执行
     * 验证结果: 不抛出异常,程序继续执行,直至程序结束
     */
    @Test
    public void method3() {
        int a = 1;
        for (int i = 0; i < 100; i++) {

            if (a > 10) {
                a = 1;
            }

            try {
                Tb2 tb2 = tbService.queryById(String.valueOf(a));
                log.info("查询出来的结果为: " + tb2.toString());
            } catch (Exception e) {
                log.error("错误信息为: " + e.getMessage() + "\r\n 报错行为: " + e.getStackTrace()[0].getLineNumber());
            } finally {
                log.info("这是第 " + i + " 次的执行结果");
                a++;
            }
        }
    }


    @Test
    @Transactional(rollbackFor = Exception.class)
    public void method4() {

        Tb2 tb2 = new Tb2();
        tb2.setScore("50");
        for (int k = 0; k < 1; k++) {

            for (int i = 0; i < 100; i++) {
                StringBuilder sbName = new StringBuilder("姓名");
                StringBuilder sbCourse = new StringBuilder("课程");
                try {
                    tb2.setName(sbName.append(i).toString());
                    tb2.setCourse(sbCourse.append(i).toString());
                    if (i % 3 == 0) {
                        tb2.setTestFiled("测试字段");
                    } else {
                        tb2.setTestFiled(String.valueOf(i));
                    }
                    Tb2 insert = tbService.insert(tb2);
                    if (null != insert) {
                        log.info("增加成功!");
                    } else {
                        log.info("增加失败");
                    }
                } catch (Exception e) {
                    log.info("错误信息为: " + e.getMessage() + "\r\n 错误行为: " + e.getStackTrace()[0].getLineNumber());
                }
            }
            tb2.setId(String.valueOf(1));
            tb2.setTestFiled(String.valueOf(1));
            Tb2 update = tbService.update(tb2);
            System.out.println("update = " + update);
        }
    }


    @Test
    public void method5() {
        List<Tb2> tb2s = tbService.queryAllByLimit(0, 0);
        //List<Tb2> tb2s = null;
        for (Tb2 tb2 : tb2s) {
            tb2.setId(null);
            tbService.insert(tb2);
        }
    }

    @Test
    public void method6() {
        Tb2 tb2 = new Tb2();
        tb2.setCourse("化学");
        List<Tb2> tb2s = tbService.queryAllByTb2(tb2);

        for (Tb2 tb : tb2s) {
            tb.setName("aaaa");
            tb.setScore("50");
            tbService.insert(tb);
        }

    }


    @Test
    @Transactional(rollbackFor = Exception.class)
    public void method7() {

        try {
            List<Tb2> tb2s = tbService.queryAllByLimit(0, 10);


            int a = 0;

            for (int i = 0; i < tb2s.size(); i++) {
                if (i == 3) {
                    Tb2 tb2 = tb2s.get(i);
                    tb2.setName("修改第四个位置的数据1111");
                    tbService.update(tb2);
                }

            }


            if (a <= 0) {
                throw new Exception("测试异常");
            }

        } catch (Exception e) {
            System.out.println("错误信息: " + e.getMessage() + ", 错误行: " + e.getStackTrace()[0].getLineNumber());
        }


    }


    /**
     * 验证mybatis模糊查询中<br/>
     * where abc like '%${abc}%' 当 ${abc} 为 null 时其执行SQL 为 where abc like '%%' 即 全表查询;<br/>
     * where abc like '%#{abc}%' 时 执行SQL为 where abc like '%?%' 其中 ? 代表传入的参数,但是该SQL不能执行,会抛出如下异常<br/>
     *
     * TypeException: Could not set parameters for mapping:<br/>
     * ParameterMapping{property='name', mode=IN, javaType=class java.lang.Object, jdbcType=null, numericScale=null, resultMapId='null', jdbcTypeName='null', expression='null'}.<br/>
     * Cause: org.apache.ibatis.type.TypeException:<br/>
     * Error setting non null for parameter #1 with JdbcType null .<br/>
     * Try setting a different JdbcType for this parameter or a different configuration property.<br/>
     * Cause: org.apache.ibatis.type.TypeException: Error setting non null for parameter #1 with JdbcType null .<br/>
     * Try setting a different JdbcType for this parameter or a different configuration property.<br/>
     * Cause: java.sql.SQLException: Parameter index out of range (1 > number of parameters, which is 0).<br/>
     *
     *
     *
     */
    @Test
    public void method8() {

        Tb2 tb2 = new Tb2();
        List<Tb2> tb2s = tbService.fuzzyQueryByName(tb2);

        for (Tb2 tb : tb2s) {
            System.out.println(tb);
        }

    }


    @Test
    public void method9(){
        Tb23 tb2 = new Tb23();
        tbService.queryAllByTb23(tb2);
    }

}
