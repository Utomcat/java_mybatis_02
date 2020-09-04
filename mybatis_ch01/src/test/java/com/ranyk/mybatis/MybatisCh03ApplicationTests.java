package com.ranyk.mybatis;

import com.ranyk.mybatis.entity.Tb2;
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
    public void method0(){
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

            if (a > 10){
                a = 1;
            }

            try {
                Tb2 tb2 = tbService.queryById(String.valueOf(a));
                log.info( "查询出来的结果为: " + tb2.toString());
            }catch (Exception e){
                log.error( "错误信息为: " + e.getMessage() + "\r\n 报错行为: " + e.getStackTrace()[0].getLineNumber());
            }finally {
                log.info("这是第 " + i + " 次的执行结果");
                a++;
            }
        }
    }


    @Test
    @Transactional(rollbackFor = Exception.class)
    public void method4(){

        Tb2 tb2 = new Tb2();
        tb2.setScore("50");
        StringBuilder sbName = new StringBuilder("姓名");
        StringBuilder sbCourse = new StringBuilder("课程");

        for (int i = 0; i < 100; i++) {
            try {
                tb2.setName(sbName.append(i).toString());
                tb2.setCourse(sbCourse.append(i).toString());
                if (i%3 == 0){
                    tb2.setTestFiled("测试字段");
                }else {
                    tb2.setTestFiled(String.valueOf(i));
                }
                Tb2 insert = tbService.insert(tb2);
                if (null != insert){
                    log.info("增加成功!");
                }else {
                    log.info("增加失败");
                }
            }catch (Exception e){
                log.info("错误信息为: " + e.getMessage() + "\r\n 错误行为: " + e.getStackTrace()[0].getLineNumber());
            }
        }
    }

}
