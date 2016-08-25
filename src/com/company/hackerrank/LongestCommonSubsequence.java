package com.company.hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by naresh.kapse on 13/06/16.
 * https://www.hackerrank.com/challenges/dynamic-programming-classics-the-longest-common-subsequence
 */
public class LongestCommonSubsequence {


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] N_M = br.readLine().split(" ");
        int N = Integer.valueOf(N_M[0]);
        int M = Integer.valueOf(N_M[1]);

        String[] s1 = br.readLine().split(" ");
        String[] s2 = br.readLine().split(" ");

        int[][] m = new int[N+1][M+1];
        String[][] output = new String[N+1][M+1];

        for (int i = 0; i <= N; i++) {
            m[i][0] = 0;
            output[i][0] = "";
        }

        for (int i = 0; i <= M; i++) {
            m[0][i] = 0;
            output[0][i] = "";
        }


        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (s1[i-1].equals(s2[j-1])) {
                    m[i][j] = m[i-1][j-1] + 1;
                    output[i][j] = output[i-1][j-1] + s1[i-1];
                }else {
                    m[i][j] = Math.max(m[i-1][j], m[i][j-1]);
                    output[i][j] = m[i-1][j] > m[i][j-1] ? output[i-1][j] : output[i][j-1];
                }
            }
        }

        for (int i = 1; i < m[N][M]; i++) {
            System.out.print(output[N][M].charAt(i-1) + " ");
        }
        System.out.print(output[N][M].charAt(m[N][M]-1));
    }
}
