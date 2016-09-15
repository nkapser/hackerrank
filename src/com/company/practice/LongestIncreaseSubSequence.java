package com.company.practice;

import java.util.Arrays;

/**
 * Created by naresh.kapse on 09/06/16.
 */
public class LongestIncreaseSubSequence {
    public int[] getLongestIncreaseSubSequence(int[] X) {
        int[] I = new int[X.length];
        for (int i = 0; i < X.length; i++) {
            I[i] = i;
        }

        int[] P = new int[X.length];
        int[] M = new int[X.length+1];

        int L = 0;

        for (int i = 0; i < X.length; i++) {
            int lo = 1;
            int hi = L;

            while(lo <= hi) {
                int mid = (int)Math.ceil((lo+hi)/2);
                if (X[M[mid]] <= X[i]) {
                    lo = mid + 1;
                }else {
                    hi = mid -1;
                }
            }

            // After searching, lo is 1 greater than the
            // length of the longest prefix of X[i]
            int newL = lo;

            // The predecessor of X[i] is the last index of
            // the subsequence of length newL-1
            P[i] = M[newL-1];
            M[newL] = i;

            // If we found a subsequence longer than any we've
            // found yet, update L
            if (newL > L)
                L = newL;
        }


        System.out.print("I: "); printArray(I);
        System.out.print("A: "); printArray(X);
        System.out.print("P: "); printArray(P);
        System.out.print("M: "); printArray(M);

        int[] S = new int[L];
        int k = M[L];
        for (int i = L-1; i > 0 ; i--) {
            S[i] = X[k];
            k = P[k];
        }

        return S;
    }

    private void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(String.format("%2d | ", arr[i]));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LongestIncreaseSubSequence lis = new LongestIncreaseSubSequence();
//        System.out.println(Arrays.toString(lis.getLongestIncreaseSubSequence(new int[]{0, 8, 4, 12, 2, 10, 6, 14})));
        System.out.println(Arrays.toString(lis.getLongestIncreaseSubSequence(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15})));
    }
}
