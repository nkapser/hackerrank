package com.company.hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Created by naresh.kapse on 13/06/16.
 * https://www.hackerrank.com/challenges/fibonacci-modified
 * Given the nth and (n+1)th terms, the (n+2)th can be computed by the following relation
 * T[n+2] = (T[n+1])^2 + T[n]
 */
public class FibonacciModified {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] l = br.readLine().split(" ");

        int N = Integer.valueOf(l[2]);

        BigInteger[] output = new BigInteger[N];
        output[0] = new BigInteger(l[0]);
        output[1] = new BigInteger(l[1]);

        for (int i = 2; i < N; i++) {
            if (output[i] == null) {
                output[i] = new BigInteger("0");
            }
            output[i] = output[i-1].pow(2).add(output[i-2]);
        }

        System.out.println(output[N-1].toString());
    }
}
