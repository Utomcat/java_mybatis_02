package com.ranyk.mybatis_ch02.mianshi;

import java.util.*;

/**
 * ClassName:Main
 * Description:2020-06-16面试
 *
 * @author ranyi
 * @date 2020-06-16 9:54
 * Version: V1.0
 */
public class Main2 {

    public static void main(String[] args) {
        Main2 main2 = new Main2();
        Scanner in = new Scanner(System.in);
        String str0 = in.next();

        if (str0.length() > 1) {
            return;
        }

        String str1 = in.next();
        int lengthSize = 100;
        if (str1.length() > lengthSize) {
            return;
        }

        int random = new Random().nextInt(str1.length());

        StringBuffer stringBuffer = new StringBuffer(str1);
        stringBuffer.insert(2, str0);
        System.out.println(stringBuffer.toString());
        //System.out.println(longestPalindrome(stringBuffer.toString()));
        //System.out.println(main2.strSub(str1));
        //main2.spiltStr(main2.strSub(str1));

        boolean flag = true;

        int length = stringBuffer.length();
        String s = main2.encode2(stringBuffer.toString());

        while (flag) {
            if (length != s.length()) {
                s = main2.encode2(s);
                length = s.length();
            } else {
                flag = false;
                length = s.length();
            }
        }

        System.out.println(stringBuffer.length() - length);


    }


    public String strSub(String str) {

        String result = "";

        char[] chars = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();

        int charSum = 0;
        String currentKey = "";
        Map<String, Integer> totalString = new HashMap<>(16);

        //统计该字符串中连续相邻的相同字符的个数
        for (int i = 0; ; i++) {
            //判断是否已经数组越界，越界退出循环
            if (i == chars.length) {
                break;
            }

            //第一个元素直接放进map中，map的key为当前的字符第一个下标
            if (i == 0) {
                currentKey = chars[i] + "_0";
                ++charSum;
                totalString.put(currentKey, charSum);
                continue;
            }

            //从第二个元素开始，每次循环和上一次的元素相对比，确定是否是同一个元素，同一个则在上个元素的上标上+1
            if (chars[i] == chars[i - 1]) {
                ++charSum;
            } else {
                charSum = 1;
                currentKey = chars[i] + "_" + i;
            }
            totalString.put(currentKey, charSum);
        }

        Set<String> strings = totalString.keySet();
        for (String sStr : strings) {
            if (totalString.get(sStr) == 1) {
                stringBuffer.append(sStr + "#");
            }
        }

        return stringBuffer.toString();
    }

    public String spiltStr(String str) {

        String[] split = str.split("#");

        //System.out.println(Arrays.toString(split));

        for (int i = 0; ; i++) {
            if (i == split.length) {
                break;
            }


        }


        return "";
    }


    public static int longestPalindrome(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        int ans = 0;
        for (int v : count) {
            ans += v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }


    public static void encode(String str) {
        if (str == null || str.equals("")) {
            System.out.println("");
            return;
        }

        // 利用StringBuffer变量字符串接受结果
        StringBuffer sb = new StringBuffer();

        char element = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (element == str.charAt(i)) {
                count++;
            } else {
                //当读到另一个不一样的字符的时候，再拼接之前统计的字符
                sb.append(element);
                //如果统计数量为1的时候不输出统计数量
                sb.append(count == 1 ? "" : count);
                element = str.charAt(i);
                count = 1;
            }
        }
        // 保证当最后一个字符和统计的数量都拼接上
        sb.append(element);
        sb.append(count == 1 ? "" : count);

        // 输出整个结果
        System.out.println(sb.toString());
    }

    public String encode2(String str) {
        if (null == str || "".equals(str)) {
            return null;
        }

        StringBuffer stringBuffer = new StringBuffer();
        char element = str.charAt(0);
        int count = 1;

        for (int i = 1; i < str.length(); i++) {
            if (element == str.charAt(i)) {
                count++;
            } else {
                //当出现另一个字符时，判断之前的统计数量是否为1，是则添加否则不添加
                if (count == 1) {
                    stringBuffer.append(element);
                }
                //重置元素和计数器
                element = str.charAt(i);
                count = 1;
            }
        }
        //追加最后一个元素
        if (count == 1) {
            stringBuffer.append(element);
        }

        return stringBuffer.toString();
    }


}
