package com.ranyk.mybatis.util;

import java.util.*;

/**
 * ClassName:ListOperate
 * Description:对List集合的操作
 *
 * @author ranyi
 * @date 2020-05-24 17:15
 * Version: V1.0
 */
public class ListOperate {

    //TODO 对List中的Map进行去重操作

    /**
     *
     * @param list 需要进行去重的List<Map<String,Object>>
     * @param mapKey 对Map中的什么key作为重复的判断依据
     * @return 返回List<Map<String, Object>>
     */
    public static List<Map<String, Object>> removeRepeatMapByKey(List<Map<String, Object>> list, String mapKey){
        if (list.size() <= 0) {
            return null;
        }

        //把list中的数据转换成msp,去掉同一id值多余数据，保留查找到第一个id值对应的数据
        List<Map<String, Object>> listMap = new ArrayList<>();
        Map<String, Map> msp = new HashMap<>();
        for(int i = list.size()-1 ; i>=0; i--){
            Map map = list.get(i);
            String id = (String)map.get(mapKey);
            map.remove(mapKey);
            msp.put(id, map);
        }
        //把msp再转换成list,就会得到根据某一字段去掉重复的数据的List<Map>
        Set<String> mspKey = msp.keySet();
        for(String key: mspKey){
            Map newMap = msp.get(key);
            newMap.put(mapKey, key);
            listMap.add(newMap);
        }
        return listMap;
    }


    //TODO 遍历List的几种方式的效率：
    // 一、使用普通的for循环
    // 二、使用iterable迭代器
    // 三、使用foreach实现循环
    // 四、使用Iterable的forEach方法
    // 五、使用list1.listIterator()方法 类似于iterable迭代器方法

    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        List<Integer> list1 = new LinkedList();
        int sum = 100000;
        for(int i = 0;i<=sum;i++){
            list.add(i);
            list1.add(i);
        }

        //获取方法执行前的时间
        long start = System.currentTimeMillis();

        //6ms
        //for (int e = 0; e <= sum; e++) {
        //    if (Integer.valueOf(sum).equals(e)){
        //        System.out.print("这是第"+list.get(e)+"打印 ");
        //    }
        //}

        //3ms
        //Iterator iterator = list.iterator();
        //while (iterator.hasNext()){
        //    Integer next = (Integer)iterator.next();
        //    if (sum ==  next ){
        //        System.out.print("这是第"+(next) +"打印 ");
        //    }
        //}
        ListIterator<Integer> integerListIterator = list1.listIterator();

        while (integerListIterator.hasNext()){
            Integer next = (Integer)integerListIterator.next();
            if (sum ==  next ){
                System.out.print("这是第"+(next) +"打印 ");
            }
        }

        //18ms  5ms
        //for (Integer i : list) {
        //    if (sum ==  i ){
        //        System.out.print("这是第"+(i) +"打印 ");
        //    }
        //}

        //89ms
        //list.forEach(e -> {
        //    if (Integer.valueOf(sum).equals(e)){
        //        System.out.print("这是第"+e+"打印 ");
        //    }
        //});

        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("ArrayList的执行时间 = "+Math.abs(start - end));




        //获取方法执行前的时间
        long start1 = System.currentTimeMillis();


        //9 ms
        //for (int e = 0; e <= sum ; e++) {
        //    if (Integer.valueOf(sum).equals(e)){
        //        System.out.print("这是第"+list.get(e)+"打印 ");
        //    }
        //}


        //2ms
        //Iterator iterator1 = list.iterator();
        //while (iterator1.hasNext()){
        //    Integer next = (Integer)iterator1.next();
        //    if (sum ==  next ){
        //        System.out.print("这是第"+(next) +"打印 ");
        //    }
        //}

        //9ms  5ms
        //for (Integer i : list1) {
        //    if (sum ==  i ){
        //        System.out.print("这是第"+(i) +"打印 ");
        //    }
        //}

        //7ms
        //list.forEach(e -> {
        //    if (Integer.valueOf(sum).equals(e)){
        //        System.out.print("这是第"+e+"打印 ");
        //    }
        //});
        list.forEach(e -> {
            if (Integer.valueOf(sum).equals(e)){
                System.out.print("这是第"+e+"打印 ");
            }
        });


        long end1 = System.currentTimeMillis();
        System.out.println();
        System.out.println("LinkList的执行时间 = "+Math.abs(start1 - end1));


    }



}
