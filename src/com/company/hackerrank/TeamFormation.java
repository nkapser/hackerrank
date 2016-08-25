package com.company.hackerrank;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by naresh.kapse on 28/07/16.
 * https://www.hackerrank.com/challenges/team-formation?h_r=next-challenge&h_v=zen
 */
public class TeamFormation {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-->0) {
            int N = s.nextInt();

            if (N == 0) {
                System.out.println("0");
                continue;
            }

            if (N == 1) {
                s.nextInt();
                System.out.println(N);
                continue;
            }

            int[] arr = new int[N];
            int maxVal = Integer.MIN_VALUE;
            int minVal = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                arr[i] = s.nextInt();
                maxVal = Math.max(maxVal, arr[i]);
                minVal = Math.min(minVal, arr[i]);
            }

            Arrays.sort(arr);

            int correction = arr[0];
            if (maxVal <= N) {
                maxVal = N+1;
            }
            int[] uniques = new int[maxVal];
            if (correction < 0) {
                maxVal = maxVal - minVal;
                uniques = new int[maxVal];
                for (int i = 0; i < N; i++) {
                    arr[i] = arr[i] - arr[0];
                    uniques[arr[i]] += 1;
                }
            }else {
                for (int i = 0; i < N; i++) {
                    uniques[arr[i]] += 1;
                }
            }

            int startIndex = arr[0];
            int endIndex = arr[N-1];

            int minSoFar = Integer.MAX_VALUE;

            for (int i = startIndex; i <= endIndex;) {
                int j = startIndex+1;
                int count = 1;

//                if (uniques[j-1] > 0 && uniques[j] > 0) {
                    while(j < N) {
                        if(arr[j-1]+1 == arr[j]){
                            uniques[j-1] = uniques[j-1]-1;
                            uniques[j] = uniques[j]-1;
                            ++count;
                            ++j;
                        }else if(arr[j-1] == arr[j]) {
                            ++j;
                            continue;
                        }else {
                            break;
                        }
                    }
//                }

                while (uniques[i] <= 0) {
                    ++i;
                }

                if (count == N) {
                    minSoFar = count;
                }

                minSoFar = Math.min(minSoFar, count);
            }

            System.out.println(minSoFar);
        }
    }
}
