package com.ranyk.mybatis_ch01;

import com.ranyk.mybatis_ch01.entity.Tb2;
import com.ranyk.mybatis_ch01.service.Tb2Service;
import com.ranyk.mybatis_ch02.util.StringOperate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
@SuppressWarnings("all")
class MybatisCh01ApplicationTests {

    @Autowired
    private Tb2Service tbService;

    @Test
    void contextLoads() {
    }

    @Test
    void test00() {
        String a = "AAAAA BBA AA";
        char b = ' ';
        log.info("在字符串 " + a + " 中出现字符 " + b + " 的次数为：" + StringOperate.statisticsStr1(a, b));
    }

    @Test
    void test01() {
        String a = "AAAAA BBA AA";
        char b = 'A';

        Map<String, Integer> map = StringOperate.statisticsStr2(a);


        log.info("在字符串 " + a + " 中出现字符 " + b + " 的次数为：" + map.get("A"));
    }

    @Test
    void test02() {
        String a = "AAAAA BBA AA";
        String b = "boo:and:foo";
        //获取字符串从指定位置开始到这个字符串结尾的子串
        String substring = a.substring(1);
        //获取字符串从指定位置开始到指定结束位置(不包含结束位置)的子串
        String substring1 = a.substring(1, 4);

        System.out.println(substring);
        System.out.println(substring1);

        //String[] split = a.split("A");
        //String[] split1 = b.split(":");
        //String[] split2 = b.split("o");
        //System.out.println(JSON.toJSONString(split));
        //System.out.println(JSON.toJSONString(split1));
        //System.out.println(JSON.toJSONString(split2));
        /*for (String str: split) {
            if (" ".equals(str)){
                System.out.print("空格");
            }
            System.out.print(str);
        }*/

    }


    @Test
    void test03() {
        String a = "AAAAA BBA AA中文汉字";
        String b = "boo:and:foo";
        Map<String, Integer> stringIntegerMap = StringOperate.statisticsStr2(a);

        //获取字符串中总共有多少个不同的字符
        int num = stringIntegerMap.size();
        //获取字符串的字符数组
        String[] split = a.split("");

        System.out.println("当前字符串中各个字符的个数为");
        if (num == split.length) {
            System.out.println("所有的字符都只有一个：");
            System.out.println(a);
        } else {
            Iterator<Map.Entry<String, Integer>> iterator = stringIntegerMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Integer> next = iterator.next();
                if (" ".equals(next.getKey())) {
                    System.out.print("字符：空格 的个数为：" + next.getValue() + " ");
                } else {
                    System.out.print("字符：" + next.getKey() + " 的个数为：" + next.getValue() + " ");
                }
            }
        }


    }


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

}
