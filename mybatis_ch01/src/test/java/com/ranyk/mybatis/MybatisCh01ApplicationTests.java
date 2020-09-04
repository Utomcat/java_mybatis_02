package com.ranyk.mybatis;

import com.ranyk.mybatis.util.StringOperate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.Map;

@Slf4j
@SpringBootTest
@SuppressWarnings("all")
class MybatisCh01ApplicationTests {

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
     * 验证 ++i 和 i++ 的区别
     * 验证结果: ++i 是 先对 i 进行加一操作,然后在对其进行赋值,即 (i = i+1; ); i++ 则是先进行赋值,然后在对其进行加一操作,即 (i = i; i+1;)
     */
    @Test
    void test04(){
        int i = 0;
        System.out.println(i);
        System.out.println(i++);
        System.out.println(i);
        System.out.println(++i);
        System.out.println(i);
    }
}
