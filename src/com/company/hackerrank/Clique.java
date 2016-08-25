package com.company.hackerrank;

import java.util.Scanner;

/**
 * Created by naresh.kapse on 30/07/16.
 * https://www.hackerrank.com/challenges/clique?h_r=next-challenge&h_v=zen
 */
public class Clique {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while (T-->0) {
            int N = s.nextInt();
            int M = s.nextInt();

            double x = -1.0;
            int r = 1;
            int n2 = (int)Math.pow(N,2.0);
            while (x < M) {
                x = n2/(2*r);
                ++r;
            }
            System.out.println(r);
        }
    }
}
