package com.company.hackerrank;

import java.util.Scanner;

/**
 * Created by naresh.kapse on 20/07/16.
 *
 * https://www.hackerrank.com/challenges/candies?h_r=next-challenge&h_v=zen
 */

public class Candies {
    public static long totalChocolates(int[] P) {
        int n = P.length;
        int minC = Integer.MAX_VALUE;
        int[] C = new int[n];

        for (int i = 1; i < n; i++) {
            int c = 0;
            if (P[i-1] == P[i]) {
                c = 1;
            }else if (P[i] > P[i-1]) {
                c = C[i-1] + 1;
            }else {
                c = C[i-1] - 1;
            }
            minC = Math.min(minC, C[i] + c);
        }

        return minC;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[] P = new int[N+1];

        for (int i = 1; i <= N; i++) {
            P[i] = s.nextInt();
        }

        System.out.println(totalChocolates(P));
    }
}
