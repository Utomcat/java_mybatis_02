package com.ranyk.mybatis;

import com.ranyk.mybatis.entity.Tb2;
import com.ranyk.mybatis.service.Tb2Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


    @Test
    public void method0(){
        Tb2 tb2 = tbService.queryById("8");
        System.out.println(tb2.toString());
    }

}
