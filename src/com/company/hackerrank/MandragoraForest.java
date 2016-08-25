package com.company.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by naresh.kapse on 19/07/16.
 *
 * https://www.hackerrank.com/challenges/mandragora
 */

public class MandragoraForest {

    private static long getMaxExperiance(int[] h) {
        int n = h.length;

        Arrays.sort(h);

        long s[] = new long[n];
        long maxP = 0;
        long totalHealth = 0;

        for (int i = 1; i < n; i++) {
            s[i] = s[i-1] + h[i];
        }

        totalHealth = s[n-1];

        for (int i = 1; i < n; i++) {
            maxP = Math.max((long)i * (totalHealth - s[i-1]), maxP);
        }

        return maxP;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            int[] h = new int[N+1];
            for (int j = 1; j <= N; j++) {
                h[j] = sc.nextInt();
            }

            System.out.println(getMaxExperiance(h));
        }
    }
}
