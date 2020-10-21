package com.ranyk.mybatis;

import com.ranyk.mybatis.util.StringOperate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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

    char cha; //0  false
    Object a; //null

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


    @Test
    void testMethod2(){
        List<String> list = null;
        list.forEach(s -> {

        });
    }


    /**
     * 验证 基本数据类型的默认值<br/>
     * char 默认值为 Unicode 编码为 0 的字符 ==> 空字符 <br/>
     * 数值类型的 默认值为 0 <br/>
     * 布尔类型默认值为 false <br/>
     * 引用数据类型默认值为 null <br/>
     */
    @Test
    void testMethod3(){
    /*    int a = 'a';
        System.out.println(cha);
        System.out.println((int)cha);
        Date date = new Date();
        if (null == date ){

        }*/

        // ''   ==> 实际传入数据类型的默认值
        // 'a'  ==> char 类型。整数类型可以转成 char 的。
        // 'aaaaa'  ==> String 类型

        char c = (char) 3.2f;
        System.out.println((float) c);
    }



}
