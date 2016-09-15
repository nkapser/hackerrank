package com.company.algos;

/**
 * Created by naresh.kapse on 09/09/16.
 */
public class KadaneAlgo {
    public int maxSubArray(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }

        int maxSoFar = arr[0];
        int maxEnding = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxEnding = Math.max(arr[i], maxEnding + arr[i]);
            maxSoFar = Math.max(maxEnding, maxSoFar);
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        KadaneAlgo ka = new KadaneAlgo();
        System.out.println(ka.maxSubArray(new int[] {2, 3, -1, -20, 5, 10}));
    }
}
