package com.ranyk.mybatis.util;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:StringOperate
 * Description:对字符串的操作
 *
 * @author ranyi
 * @date 2020-05-24 17:21
 * Version: V1.0
 */
public class StringOperate {

    //TODO 对字符串中的某个字符进行统计有多少个

    /**
     * 统计字符串中的某个字符出现的次数：
     * 处理逻辑：将字符串转成字符数组，遍历对应的字符数，某个字符出现一次则计数一次
     *
     * @param originalCharacter 需要统计的字符串
     * @param str               需要查找的字符
     * @return 返回计数的值
     */
    public static int statisticsStr1(String originalCharacter, Character str) {
        char[] chars = originalCharacter.toCharArray();
        int count = 0;
        for (char ch : chars) {
            if (str.equals(ch)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 统计指定字符串中各个字符的数量
     *
     * @param str 需要进行统计的字符串
     * @return 返回存放各个字符数量的Map集合
     */
    public static Map<String, Integer> statisticsStr2(String str) {

        Map<String, Integer> count = new HashMap<>(16);
        //将字符串分割 每一个字符单独作为一个字符串放进字符串数组中
        String[] mySirs = str.split("");
        //循环此字符串数组，将其对应的每个字符放进Map的key中，然后每个字符的数量统计放进Map的value中
        for (String mySir : mySirs) {
            int totalNum = 1;
            if (count.containsKey(mySir)) {
                totalNum = count.get(mySir) + 1;
            }
            count.put(mySir, totalNum);
        }

        return count;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 需要判定的字符串
     * @return 返回判定结构
     */
    public static Boolean judgeStringIsNull(String str) {
        return StringUtils.hasText(str);
    }


    /**
     * 字符串替换
     *
     * @param originalStr 原始字符串
     * @param targetStr 需要替换的目标字符串
     * @param replaceStr 替换字符串
     * @return 返回替换结果
     * @throws Exception 抛出异常: 不存在需要替换的字符串异常
     */
    public static String replaceStr(String originalStr, String targetStr, String replaceStr) throws Exception{
        if (!originalStr.contains(targetStr)){
            throw new Exception("原始字符串不包含需要更换的子字符串！");
        }

        return originalStr.replace(targetStr,replaceStr);
    }
}
