package com.ranyk.mybatis.mianshi;

import java.util.ArrayList;
import java.util.List;

/**
 * 2 * @Author: zhouqy
 * 3 * @Description:
 * 4 * @Date: 2020/6/17 16:44
 * 5
 */
public class T3 {
    public static class GameO{
        private String str;
        private int index;
        private String indexValue;
    }
    public static void main(String[] args) {
        String a = "ABCBCCCAA";
        List<String> list = new ArrayList<>();
        int len = a.length()+1;
        String temp = "";
        String val="";

        //消除前值
        String beforeValue = "";
        //消除后值
        String afterValue = "";
        //添加的值
        String addValue = "";
        //添加的索引
        int addIndex = 0;
        //最小结果长度
        int minResult = 0;
         for (int i = 0; i < len; i++) {
            temp = "";
            val="";
            for (int j = 0; j <3 ; j++) {
                switch (j){
                    case 0:val="A";break;
                    case 1:val="B";break;
                    case 2:val="C";break;
                    default:break;
                }
                if(i==0){
                    temp = val+a;
                }else if(i==len-1){
                    temp = a +val;
                }else {
                    temp = a.substring(0,i)+val+a.substring(i);
                }
                String re = Calculate1.getMinResult(temp);
                int resultLen = re.length();
                 if(i == 0){
                     minResult = resultLen;
                     beforeValue = temp;
                     afterValue = re;
                     addIndex = i;
                     addValue =val;
                 }else if(resultLen < minResult){
                     minResult = resultLen;
                     beforeValue = temp;
                     afterValue = re;
                     addIndex = i;
                     addValue = val;
                 }

             }
        }

        System.out.println("在\""+a+"\"的第"+addIndex+"个字符后插入"+addValue+"得到\""+beforeValue+"\""+
                ",消除后得到"+afterValue+"总共消除"+(10-minResult)+"个字符");
    }



 }
