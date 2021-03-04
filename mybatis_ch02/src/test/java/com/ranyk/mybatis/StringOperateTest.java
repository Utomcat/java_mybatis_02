package com.ranyk.mybatis;

import com.ranyk.mybatis.excel.vo.ComplexHeadData;
import com.ranyk.mybatis.util.StringOperate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * CLASS_NAME: StringOperateTest<br/>
 * Description: 字符串操作测试类<br/>
 *
 * @author ranYk <br/>
 * @version 1.0
 */
@Slf4j
class StringOperateTest {

    private String regxExp = "^[1-9]{1}$";

    @Test
    void getAge() {
        log.info("获取的年纪为: {}", StringOperate.getAge(96, 12, 16));
    }

    @Test
    void getPersonInfoByIdCard() {
        log.info("获取到的个人信息为: {}", StringOperate.getPersonInfoByIdCard("510824199612161637"));
    }

    @Test
    void test01() {
        log.info("number: {}", (2 << 15) % 11);
    }


    @Test
    void test02() {
        boolean valid = StringOperate.verifyValid("510824199612161637", 15);
    }


    @Test
    void test03() {
        String x = "12333";
        String y = "0";
        String z = "3";
        String reg = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$";
        String id = "510824961216163";
        String id2 = "510824961216163xx";

        log.info("{}", id2.toUpperCase());

        if (Pattern.matches(reg, id)) {
            log.info("匹配成功!");
        } else {
            log.info("匹配失败!");
        }

    }

    @Test
    void test04() {
        log.info(" 当前的时间为:  {}", LocalDate.now().toString() + LocalDateTime.now().toString());
        log.info(" ComplexHeadData class 对象调用getName方法后的结果:  {}", ComplexHeadData.class.getName().substring(ComplexHeadData.class.getName().lastIndexOf(".") + 1));

    }


    @Test
    void test05() {
        String dateTime = MessageFormat.format("{0,date,yyyy-MM-dd HH:mm:ss:ms}", new Object[]{new Date(System.currentTimeMillis())});
        log.info("格式化时间 {}", dateTime);
    }


    @Test
    void test06() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        Date d = new Date();
        String str = sdf.format(d);
        log.info("格式化时间字符串: {}", str);

        SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd" );
        Date d1= new Date();
        String str1 = sdf1.format(d1);
        log.info("格式化时间字符串: {}", str1);
    }


    @Test
    void test07(){
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss" );
        String str = "2016/1/5 12:12:12";
        try {
            Date d = sdf.parse(str);
            System.out.printf("字符串 %s 通过格式  yyyy/MM/dd HH:mm:ss %n转换为日期对象: %s %n",str,d.toString());
            log.info("字符串 {} 通过格式  yyyy/MM/dd HH:mm:ss %n转换为日期对象: {}",str,d.toString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Test
    void test08(){
        Calendar instance = Calendar.getInstance();
        Date time = instance.getTime();
        log.info("{}",time);
    }


    @Test
    void Test09(){
        Date date = new Date(0);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        log.info("{}",instance.getTime());
    }

    @Test
    void test10(){
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        log.info("操作日历对象之前的时间为 {}",instance.getTime());
        instance.add(Calendar.DAY_OF_MONTH,-3);
        log.info("操作日历对象...");
        log.info("操作日历对象之后的时间为 {}",instance.getTime());

    }


    @Test
    void test11(){
        String dateStr = "20210304";
        DateTimeFormatter basicIsoDate = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDate parse = LocalDate.parse(dateStr, basicIsoDate);
        log.info("{}",parse);
    }


}