package com.ranyk.mybatis_ch02.mianshi;

/**
 * ClassName:Main3
 * Description:力扣第949题
 *
 * 给定一个由 4 位数字组成的数组，返回可以设置的符合 24 小时制的最大时间。
 *
 * 最小的 24 小时制时间是 00:00，而最大的是 23:59。从 00:00 （午夜）开始算起，过得越久，时间越大。
 *
 * 以长度为 5 的字符串返回答案。如果不能确定有效时间，则返回空字符串。
 *
 * 示例 1：
 *
 * 输入：[1,2,3,4]
 * 输出："23:41"
 * 示例 2：
 *
 * 输入：[5,5,5,5]
 * 输出：""
 *  
 *
 * 提示：
 *
 * A.length == 4
 * 0 <= A[i] <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-time-for-given-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ranyi
 * @date 2020-06-16 17:48
 * Version: V1.0
 */
public class Main3 {

    public static void main(String[] args) {
        Main3 main3 = new Main3();
        int[] a = {1,2,3,4};
        System.out.println(main3.largestTimeFromDigits(a));
    }


    public String largestTimeFromDigits(int[] A) {
        int ans = -1;

        // Choose different indices i, j, k, l as a permutation of 0, 1, 2, 3
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (j != i) {
                    for (int k = 0; k < 4; ++k) {
                        if (k != i && k != j) {
                            int l = 6 - i - j - k;

                            // For each permutation of A[i], read out the time and
                            // record the largest legal time.
                            int hours = 10 * A[i] + A[j];
                            int mins = 10 * A[k] + A[l];
                            if (hours < 24 && mins < 60) {
                                ans = Math.max(ans, hours * 60 + mins);
                            }
                        }
                    }
                }
            }
        }

        return ans >= 0 ? String.format("%02d:%02d", ans / 60, ans % 60) : "";
    }

}
