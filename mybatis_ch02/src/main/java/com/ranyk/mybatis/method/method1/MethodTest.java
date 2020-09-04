package com.ranyk.mybatis.method.method1;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:MethodTest
 * Description:方法验证测试类一
 *
 * @author ranyi
 * @date 2020-08-26 10:40
 * Version: V1.0
 */
public class MethodTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        System.out.println(list + ", 列表长度：" + list.size());

        String a = "1000";
        String b = "1000";
        listStore(list,a,b);

        System.out.println("start = " + a + ", end = " + b);

        System.out.println(list + ", 列表长度：" + list.size());

        stringStore(a,b);

        System.out.println("start = " + a + ", end = " + b);


    }

    private static void listStore(List<String> list, String start, String end) {
        // 1. 开始楼栋ID 和 结束楼栋ID 进行排序，并放进参数 list 中
        int compare = Integer.compare(Integer.valueOf(start), Integer.valueOf(end));

        if (compare == 0) {
            //1.1. 起始楼栋编号 等于 终止楼栋编号
            list.add(start);

        } else {
            if (compare < 0) {
                //1.2. 起始楼栋编号 小于 终止楼栋编号
                list.add(start);
                list.add(end);
            } else {
                //1.3. 起始楼栋编号 大于 终止楼栋编号
                list.add(end);
                list.add(start);
            }
        }
    }

    private static void listStore2(List<String> list){

    }

    private static void stringStore(String start, String end){
        String temp = "";
        int compare = Integer.compare(Integer.valueOf(start), Integer.valueOf(end));
        if (compare == 0){
            return;
        }else {
            if (compare < 0) {
                return;
            }else {
                temp = start;
                start = end;
                end = temp;
            }
        }
    }

}
