package com.ranyk.mybatis;

import com.ranyk.mybatis.excel.CreateExcelUseEasyExcel;
import com.ranyk.mybatis.excel.CreateExcelUseJxl;
import com.ranyk.mybatis.util.ObjectOperate;
import com.ranyk.mybatis.util.StringOperate;
import com.ranyk.mybatis.util.vo.PersonInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
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
    void test04() {
        int i = 0;
        System.out.println(i);
        System.out.println(i++);
        System.out.println(i);
        System.out.println(++i);
        System.out.println(i);
    }


    /**
     * & 和 && 的差异验证<br/>
     * 两者都可表示与操作,但是 & 常进行 位运算; && 常进行 逻辑与运算;<br/>
     * 当两者 前后均为 布尔值 时,两者可互换,且都支持 一假则假;<br/>
     * 当为数字是 & 代表位运算; && 不支持该种运算;
     */
    @Test
    void test05() {
        log.info("& 的值 {}", 1 & 2);
        log.info("& 的值 {}", 1 != 1 & 2 != 2);
        log.info("&& 的值 {}", 1 == 1 && 2 == 2);
    }

    /**
     * Map 中value 中存放的是 boolean 值时,获取值时强转类型测试
     */
    @Test
    void test06() {
        Map<String, Object> map = new HashMap<>(3);
        map.put("a", false);
        map.put("b", true);
        map.put("c", null);
        if (!(boolean) map.get("a")) {
            log.info("false 能实现转换");
        }
        if ((boolean) map.get("b")) {
            log.info("true 能实现转换");
        }
        try {
            if (!(boolean) map.get("c")) {
                log.info("null 能实现转换");
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
    }


    /**
     * Date 类型判空测试
     */
    @Test
    void test07() {
        Date date = null;
        log.info("当前Date类型的值 {} ", ObjectOperate.objectIsEmpty(date) ? "是空" : "不是空");
        date = new Date();
        log.info("当前Date类型的值 {} ", ObjectOperate.objectIsEmpty(date) ? "是空" : "不是空");
    }


    /**
     * 对象实例的属性全空判断测试
     */
    @Test
    void test08() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setAge(25);
        log.info("无参构造函数构造的对象 {} ", ObjectOperate.objectIsEmpty(personInfo, true) ? "是空" : "不是空");
        log.info("{}", personInfo);
    }


    /**
     * 测试使用 jxl 方式操作Excel
     */
    @Test
    void test09() {
        try {
            //1. 创建一个output 输出对象
            File file = new File("A.xls");
            OutputStream outputStream = new FileOutputStream(file);
            //2. 创建Excel
            CreateExcelUseJxl.createExcel(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试使用 easyExcel 方式操作Excel
     */
    @Test
    void test10() {
        log.info("开始生成Excel");
        CreateExcelUseEasyExcel easyExcel = new CreateExcelUseEasyExcel();
        easyExcel.complexHeadWrite();
        log.info("Excel生成完成");
    }
}
