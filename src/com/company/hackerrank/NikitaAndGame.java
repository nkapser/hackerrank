package com.company.hackerrank;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by naresh.kapse on 06/07/16.
 *
 * https://www.hackerrank.com/challenges/array-splitting?h_r=next-challenge&h_v=zen
 */
public class NikitaAndGame {
    public static long printMaxPoints(long[] arr, int s, int e) {
        long count = 0;

        if (s==e) {
            return count;
        }

        long[] la = new long[arr.length];
        long[] ra = new long[arr.length];

        int l = arr.length;
        la[s] = 0;
        ra[e] = 0;

        for (int i = s; i <= e; i++) {
            if (i==s) {
                la[i] = arr[i];
            }else {
                la[i] = la[i-1] + arr[i];
            }
        }

        for (int i = e; i >= s; i--) {
            if (i==e) {
                ra[i] = arr[i];
            }else {
                ra[i] = ra[i+1] + arr[i];
            }
        }

        for (int i = s; i < e; i++) {
            if (la[i] == ra[i+1]) {
                if (i-s == e-(i+1)) {
                    count = 1 + Math.max(printMaxPoints(arr, s, i), printMaxPoints(arr, i+1, e));
                }else if(e-(i+1) > i-s){
                    count = 1 + printMaxPoints(arr, i+1, e);
                }else {
                    count = 1 + printMaxPoints(arr, s, i);
                }
            }
        }

        return count;
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
            System.out.println(printMaxPoints(arr, 0, N-1));
        }
    }
}
