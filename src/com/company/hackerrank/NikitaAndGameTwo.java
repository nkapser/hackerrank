package com.company.hackerrank;

import java.util.Scanner;

/**
 * Created by naresh.kapse on 06/07/16.
 *
 * https://www.hackerrank.com/challenges/array-splitting?h_r=next-challenge&h_v=zen
 */
public class NikitaAndGameTwo {
    public static long printMaxPoints(long[] arr, int s, int e) {
        long count = 0;

        if (s==e) {
            return count;
        }

//        for (int i = s; i < e; i++) {
//            if (la[i] == ra[i+1]) {
//                if (i-s == e-(i+1)) {
//                    count = 1 + Math.max(printMaxPoints(arr, s, i), printMaxPoints(arr, i+1, e));
//                }else if(e-(i+1) > i-s){
//                    count = 1 + printMaxPoints(arr, i+1, e);
//                }else {
//                    count = 1 + printMaxPoints(arr, s, i);
//                }
//            }
//        }

        return count;
    }

    public static long getMaxPoints(long[] a, int s, int e) {
        long[][] m = new long[e+2][e+1];
        for (int i = s; i <= e ; i++) {
            int k = i;
            int l = e + 1 - i;
            for (int j = k; j <= e; j++) {
                if (j==s) {
                    m[k][j]=a[i];
                }else if (i!=e) {
                    m[k][j] = m[k][j-1] + a[j];
                }
            }
            for (int j = e; j > s; j--) {
                if (j==l-1) {
                    m[l][j]=a[j];
                }else {
                    System.out.println("l:"+l+"|j"+j);
//                    m[l][j] = m[l][j+1] + a[j];
                }
            }
        }

        for (int i = 0; i <= (e+1) ; i++) {
            for (int j = 0; j <= e ; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
        return printMaxPoints(a, s, e);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            long[] arr = new long[N];
            for (int j = 0; j < N; j++) {
                arr[j] = sc.nextInt();
            }
            System.out.println(getMaxPoints(arr, 0, N-1));
        }
    }
}
