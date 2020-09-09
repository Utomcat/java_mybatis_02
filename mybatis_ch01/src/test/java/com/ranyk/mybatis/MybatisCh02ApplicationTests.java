package com.ranyk.mybatis;

import com.ranyk.mybatis.util.StringOperate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Set;

/**
 * ClassName:MybatisCh02ApplicationTests
 * Description:测试类二
 *
 * @author ranyi
 * @date 2020-09-04 16:30
 * Version: V1.0
 */
@Slf4j
@SpringBootTest
public class MybatisCh02ApplicationTests {

    @Test
    void testMethod0(){
        String str = "adsaffasdfskdjkfdjklsjadjflsdjflksajdkfjlsdakjflsajdfjsdfldksjflkjasldf";
        Character b = 'a';
        log.info("在字符串 " + str + " 中出现字符 " + b + " 的次数为：" + StringOperate.statisticsStr1(str, b));
    }

    @Test
    void testMethod1(){
        String str = "adsaffasdfskdjkfdjklsjadjflsdjflksajdkfjlsdakjflsajdfjsdfldksjflkjasldf";
        Map<String, Integer> map = StringOperate.statisticsStr2(str);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            log.info( "在字符串 " + str + "中, 字符: " + entry.getKey() + " , 出现了: " + entry.getValue() + "次！");
        }
    }

}
