package com.ranyk.mybatis_ch02.mianshi;

/**
 * 2 * @Author: zhouqy
 * 3 * @Description:
 * 4 * @Date: 2020/6/17 18:30
 * 5
 */
public class Calculate {

    public static void main(final String[] args) {
        // int a = 266052;
        final int a = 999999;
        final String a1 = Integer.toString(a);
        final String[] split = a1.split("");
        final int[] arr = new int[10];
        final int[] arr1 = new int[10];
        for (final String s : split) {
            System.out.println(s);
            final int index = Integer.valueOf(s);
            arr[index]++;
            arr1[index]++;
        }
        // int[] clone = arr.clone();
        System.out.println(calculateMax(arr));
        System.out.println(calculateMin(arr1));

    }

    public static String calculateMax(final int[] arr) {

        int h1 = 0;
        int h2 = 0;
        int m1 = 0;
        int m2 = 0;
        int s1 = 0;
        int s2 = 0;
        // boolean flag = false;
        // 获取h1
        h1 = getMaxResult(arr, 2);
        h2 = getMaxResult(arr, 3);
        m1 = getMaxResult(arr, 5);
        m2 = getMaxResult(arr, 9);
        s1 = getMaxResult(arr, 5);
        s2 = getMaxResult(arr, 9);

        return wrapper(h1 * 10 + h2) + ":" + wrapper(m1 * 10 + m2) + ":" + wrapper(s1 * 10 + s2);
    };

    public static String wrapper(final int i) {
        return i >= 10 ? i + "" : "0" + i;
    }

    public static String calculateMin(final int[] arr) {

        int h1 = 0;
        int h2 = 0;
        int m1 = 0;
        int m2 = 0;
        int s1 = 0;
        int s2 = 0;
        // boolean flag = false;
        // 获取h1
        h1 = getMinResult(arr, 2);
        h2 = getMinResult(arr, 3);
        m1 = getMinResult(arr, 5);
        m2 = getMinResult1(arr, 9);
        s1 = getMinResult(arr, 5);
        s2 = getMinResult(arr, 9);

        return wrapper(h1 * 10 + h2) + ":" + wrapper(m1 * 10 + m2) + ":" + wrapper(s1 * 10 + s2);
    };

    public static int getMaxResult(final int[] arr, final int max) {
        int result = 0;
        boolean flag = false;
        // 获取s2
        for (int i = max; i >= 0; i--) {
            if (arr[i] > 0) {
                result = i;
                arr[i]--;
                flag = true;
                break;
            }
        }
        check(flag);
        return result;
    }

    public static int getMinResult(final int[] arr, final int min) {
        int result = 0;
        boolean flag = false;
        // 获取s2
        for (int i = 0; i <= min; i++) {
            if (arr[i] > 0) {
                result = i;
                arr[i]--;
                flag = true;
                break;
            }
        }
        check(flag);
        return result;
    }

    public static int getMinResult1(final int[] arr, final int max) {
        int result = 0;
        boolean flag = false;
        // 获取s2
        for (int i = 0; i <= max; i++) {
            if (arr[i] > 0) {
                result = i;
                if (i == 5) {
                    if (check1(arr, 5)) {
                        arr[i]--;
                        flag = true;
                        break;
                    }
                }

                else {
                    arr[i]--;
                    flag = true;
                    break;
                }
            }
        }
        check(flag);
        return result;
    }

    public static boolean check1(final int[] arr, final int index) {
        for (int j = 0; j < index; j++) {
            if (arr[j] > 0 && arr[index] > 1) {
                return true;
            }
        }
        return false;

    }

    public static void check(final boolean f) {
        if(!f){
            //throw new RuntimeException("不合符");
            System.out.println(0);
        }
    }
}
