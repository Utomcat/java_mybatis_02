package com.ranyk.mybatis_ch02.datatype.list;

import com.alibaba.fastjson.JSON;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:ListTest
 * Description:List结构学习一
 *
 * @author ranyi
 * @date 2020-08-18 12:22
 * Version: V1.0
 */
public class ListTest {

    public static void main(String[] args) {
        /*List<Integer> list = new ArrayList<>();
        list.add(1);
        list = method2(list);
        System.out.println(list.size());*/

        List<Man> list = new ArrayList<>();
        Man man = new Man("张三", 20);
        list.add(man);
        List<Man> list1 = method3(list);
        for (Man man1 : list1) {
            System.out.println(man1);
        }

        for (int i = 0; i < 1; i++) {
            Man man1 = list1.get(i);
            man1.setName("李四");
            man1.setAge(40);
            list1.add(i,man1);
        }



        for (Man man1 : list1) {
            System.out.println(man1);
        }

    }


    public static void method1(){
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("2222");
        System.out.println(list.toString());
        System.out.println(JSON.toJSONString(list));
    }

    /**
     * 在对 List 进行遍历的时候不能对其进行修改，否则会抛出  ConcurrentModificationException
     * @param list
     * @return
     */
    public static List<Integer> method2(List<Integer> list){
        List<Integer> list1 = new ArrayList<>();
        for (Integer o : list) {
            for (int i = 0; i < 1000; i++) {
                list.add(o);
            }
        }
        return list1;
    }


    public static List<Man> method3(List<Man> list){

        List<Man> list1 = new ArrayList<>();

        for (Man man : list) {
            for (int i = 0; i < 100; i++) {
                list1.add(man);
            }
        }

        return list1;
    }

}

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
class Man {
    private String name;
    private Integer age;
}
