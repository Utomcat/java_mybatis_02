package com.ranyk.mybatis.mianshi;

import java.util.Scanner;

/**
 * ClassName:Main
 * Description:2020-06-16面试
 *
 * @author ranyi
 * @date 2020-06-16 10:11
 * Version: V1.0
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int length = 6;
        int[] array = new int[6];

        Scanner in = new Scanner(System.in);
        for (int i = 0; i < length; i++) {
            int nextInt = in.nextInt();
            if (nextInt > 9 || nextInt < 0) {
                return;
            }
            array[i] = nextInt;
        }

        System.out.println(main.miTimeFromDigits(array) + " " + main.maxTimeFromDigits(array));


    }

    public static String largestTimeFromDigits(int[] A) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int sum = 23; sum >= 0; sum--) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A.length; j++) {
                    if (i != j && A[i] * 10 + A[j] == sum) {
                        // 找到小时数后找分钟数
                        for (int fen = 59; fen >= 0; fen--) {
                            for (int m = 0; m < A.length; m++) {
                                for (int n = 0; n < A.length; n++) {
                                    if (m != n && A[m] * 10 + A[n] == fen && m != i && m != j && n != i && n != j) {
                                        //找到分钟后找秒
                                        for (int second = 59; second >= 0; second--) {
                                            for (int x = 0; x < A.length; x++) {
                                                for (int y = 0; y < A.length; y++) {
                                                    if (x != y && A[x] * 10 + A[y] == second && x != m && y != n && x != n && y != m && x != i && y != j && y != i && x != j) {
                                                        stringBuilder.append(A[i]);
                                                        stringBuilder.append(A[j]);
                                                        stringBuilder.append(':');
                                                        stringBuilder.append(A[m]);
                                                        stringBuilder.append(A[n]);
                                                        stringBuilder.append(':');
                                                        stringBuilder.append(A[x]);
                                                        stringBuilder.append(A[y]);
                                                        return stringBuilder.toString();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        stringBuilder.append(0);

        return stringBuilder.toString();
    }

    public static String minTimeFromDigits(int[] A) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int sum = 0; sum <= 23; sum++) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A.length; j++) {
                    if (i != j && A[i] * 10 + A[j] == sum) {
                        // 找到小时数后找分钟数
                        for (int fen = 0; fen <= 59; fen++) {
                            for (int m = 0; m < A.length; m++) {
                                for (int n = 0; n < A.length; n++) {
                                    if (m != n && A[m] * 10 + A[n] == fen && m != i && m != j && n != i && n != j) {
                                        //找到分钟后找秒
                                        for (int second = 0; second <= 59; second++) {
                                            for (int x = 0; x < A.length; x++) {
                                                for (int y = 0; y < A.length; y++) {
                                                    if (x != y && A[x] * 10 + A[y] == second && x != m && y != n && x != n && y != m && x != i && y != j && y != i && x != j) {
                                                        stringBuilder.append(A[i]);
                                                        stringBuilder.append(A[j]);
                                                        stringBuilder.append(':');
                                                        stringBuilder.append(A[m]);
                                                        stringBuilder.append(A[n]);
                                                        stringBuilder.append(':');
                                                        stringBuilder.append(A[x]);
                                                        stringBuilder.append(A[y]);
                                                        return stringBuilder.toString();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        stringBuilder.append(0);
        return stringBuilder.toString();
    }

    public String miTimeFromDigits(int[] A) {
        int ans = -1;
        boolean count = true;


        // Choose different indices i, j, k, l as a permutation of 0, 1, 2, 3, 4, 5
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A.length; ++j) {
                if (j != i) {
                    for (int k = 0; k < A.length; ++k) {
                        for (int l = 0; l < A.length; ++l) {
                            if (l != k) {
                                if (k != i && k != j && l != i && l != j) {
                                    for (int m = 0; m < A.length; m++) {
                                        if (m != l && m != k && m != j && m != i) {
                                            int n = 15 - i - j - k - l - m;
                                            // For each permutation of A[i], read out the time and
                                            // record the smallest legal time.
                                            int hours = 10 * A[i] + A[j];
                                            int mins = 10 * A[k] + A[l];
                                            int second = 10 * A[m] + A[n];


                                            if (hours < 24 && mins < 60 && second < 60) {
                                                if (count) {
                                                    ans = (hours * 60 * 60) + (mins * 60) + second;
                                                    count = false;
                                                }
                                                ans = Math.min(ans, (hours * 60 * 60) + (mins * 60) + second);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return ans >= 0 ? String.format("%02d:%02d:%02d", ans / 3600, (ans - (ans / 3600) * 3600) / 60, ans % 60) : "0";
    }

    public String maxTimeFromDigits(int[] A) {
        int ans = -1;

        // Choose different indices i, j, k, l as a permutation of 0, 1, 2, 3
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A.length; ++j) {
                if (j != i) {
                    for (int k = 0; k < A.length; ++k) {
                        for (int l = 0; l < A.length; ++l) {
                            if (l != k) {
                                if (k != i && k != j && l != i && l != j) {
                                    for (int m = 0; m < A.length; m++) {
                                        if (m != l && m != k && m != j && m != i) {
                                            int n = 15 - i - j - k - l - m;
                                            // For each permutation of A[i], read out the time and
                                            // record the largest legal time.
                                            int hours = 10 * A[i] + A[j];
                                            int mins = 10 * A[k] + A[l];
                                            int second = 10 * A[m] + A[n];
                                            if (hours < 24 && mins < 60 && second < 60) {
                                                ans = Math.max(ans, (hours * 60 * 60) + (mins * 60) + second);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return ans >= 0 ? String.format("%02d:%02d:%02d", ans / 3600, (ans - (ans / 3600) * 3600) / 60, ans % 60) : "0";
    }


}
