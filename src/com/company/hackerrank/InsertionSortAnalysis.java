package com.company.hackerrank;

import java.util.*;

/**
 * Created by naresh.kapse on 21/07/16.
 */
public class InsertionSortAnalysis {
    private static void swap(int[] arr, int i, int j) {
        int k = arr[j];
        arr[j] = arr[i];
        arr[i] = k;
    }

    private static int jumps(int[] arr, int size) {
        int noOfShifts = 0;
        for (int i = 0; i < size-1; i++) {
            for (int j = i+1; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    swap(arr, j-1, j);
                    ++noOfShifts;
                }
            }
        }

        return noOfShifts;
    }

    private static int jumpsUsingMergeSort(int[] arr, int l, int h, int count) {
        if (l < h) {
            int m = (l+h)/2;
            count = jumpsUsingMergeSort(arr, l, m, count);
            count = jumpsUsingMergeSort(arr, m+1, h, count);
            count = count + merge(arr, l, m, h);
        }
        return count;
    }

    private static int merge(int[] arr, int l, int m, int h) {
        int count = 0;


        for (int j = m+1; j <= h; j++) {
            int k = m;
            int t = 0;
            while(k >= l && arr[j] < arr[k]) {
                ++t;
                k--;
            }
            int s = m+1-t;
            count = t + count;
            if (j != s && s >= l)
                swap(arr, j, s);
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();

        while (T-->0){
            int L = s.nextInt();
            int[] arr = new int[L];
            for (int i = 0; i < L; i++) {
                arr[i] = s.nextInt();
            }
            System.out.println(jumpsUsingMergeSort(arr,0,L-1,0));
        }
    }
}
