package com.ranyk.mybatis_ch02.variable;

/**
 * ClassName:MyTest4
 * Description:Java基础验证
 *
 * @author ranyi
 * @date 2020-06-14 22:47
 * Version: V1.0
 */
public class MyTest4 {

    public static void main(String[] args) {

        //1. instanceof的验证 instanceof 不能用于基本数据类型的验证，只能用于引用数据类型或基本数据类型的包装类
        /*MyTest4 myTest4 = new MyTest4();

        if (myTest4 instanceof Object){

            System.out.println("myTest4 是属于 Object 类");

        }else {

            System.out.println("myTest4 是属于 Object 类");

        }*/

        //2. Char类型的转换
        /*Character a = '9';
        Integer a2int = Integer.valueOf(a);
        double a2double = a;
        float a2float = a;
        if (a instanceof  Character){
            System.out.println("a = " + a);
        }

        if (a2int instanceof Integer) {
            System.out.println("a2int = " + a2int);
        }

        System.out.println("a2double = " + a2double);
        System.out.println("a2float = " + a2float);*/

        /*char char_a = 'a';
        int char_a_int = char_a;
        long char_a_long = char_a;
        float char_a_float = char_a;
        double char_a_double = char_a;
        short char_a_short = (short) char_a;
        byte char_a_byte = (byte) char_a;

        System.out.println("字符型数据 char_a = " + char_a);
        System.out.println("int型数据 char_a_int = " + char_a_int);
        System.out.println("short型数据 char_a_short = " + char_a_short);
        System.out.println("byte型数据 char_a_byte = " + char_a_byte);
        System.out.println("long型数据 char_a_byte = " + char_a_long);
        System.out.println("float型数据 char_a_byte = " + char_a_float);
        System.out.println("double型数据 char_a_byte = " + char_a_double);

        short short_a = 'a';
        byte short_a_byte = (byte) short_a;

        System.out.println("short 的数据类型为 short_a = " + short_a);
        System.out.println("byte 的数据类型为 short_a_byte = " + short_a_byte);*/

        //3. == 和 equals 的比较
        /*Integer a = 100;
        Integer b = 100;
        Integer c = 200;
        Integer d = 200;
        Integer e = -128;
        Integer f = -128;
        Integer g = 127;
        Integer h = 127;
        Integer i = -129;
        Integer j = -129;
        Integer k = 128;
        Integer l = 128;

        System.out.println("a和b的比较1：" +  (a == b));
        System.out.println("a和b的比较2：：" + a.equals(b));
        System.out.println("c和d的比较1：" +  (c == d));
        System.out.println("c和d的比较2：" +  c.equals(d));
        System.out.println("e和f的比较1：" +  (e == f));
        System.out.println("e和f的比较2：" +  e.equals(f));
        System.out.println("g和h的比较1：" + (g == h));
        System.out.println("g和h的比较2：" + g.equals(h));
        System.out.println("i和j的比较1：" + (i == j));
        System.out.println("i和j的比较2：" + i.equals(j));
        System.out.println("k和l的比较1：" + (k == l));
        System.out.println("k和l的比较2：" + k.equals(l));*/

        //4. 浮点型数据运算出现的误差的问题的解决办法 BigDecimal
        /*BigDecimal bigDecimal = new BigDecimal("1100111");

        System.out.println(bigDecimal);*/

        //5. 数组的实例化
        int[] arr = new int[1];
        int[] arr1 = new int[]{1};
        int arr2[] = new int[1];
        int arr3[] = new int[]{1};
        int[] arr4 = {1};
        int[] arr5 = new int[]{1};



    }

}
