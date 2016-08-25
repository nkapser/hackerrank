package com.company.hackerrank;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.Arrays;

/**
 * Created by naresh.kapse on 28/07/16.
 *
 * https://www.hackerrank.com/challenges/crush?h_r=next-challenge&h_v=zen
 */
public class AlgorithmsCrush {
    public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

        int N = s.nextInt();
        int M = s.nextInt();

        long[] v = new long[N+1];
        long maxVal = 0;

        while (M-->0) {
            int a = s.nextInt();
            int b = s.nextInt();
            long k = s.nextLong();

            v[a] = v[a] + k;
            if (b+1 <= N) {
                v[b+1] = v[b+1] - k;
            }

//            This is O(N^2*M) = O(N^3)
//            for (int i = a; i <= b; i++) {
//                v[i] = v[i] + k;
//                if (v[i] > maxVal) {
//                    maxVal = v[i];
//                }
//            }
        }

        long x = 0;
        for (int i = 1; i <= N; i++) {
            x = x + v[i];
            if (maxVal < x) {
                maxVal = x;
            }
        }

        System.out.println(maxVal);
    }
}
