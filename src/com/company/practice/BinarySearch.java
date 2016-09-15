package com.company.practice;

/**
 * Created by naresh.kapse on 10/06/16.
 */
public class BinarySearch {

    private int binarySearch(int[] arr, int lo, int hi, int target) {
        if (lo == hi) {
            return lo;
        }else {
            int mid = (lo+hi) / 2;
            if (target <= arr[mid]) {
                return binarySearch(arr, lo, mid, target);
            }else {
                return binarySearch(arr, mid+1, hi, target);
            }
        }
    }

    public int findElement(int[] arr, int target) {
        return binarySearch(arr, 0, arr.length-1, target);
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] arr = new int[]{1,3,5,8,10,13,15,17,19};
        System.out.println("Index : " +bs.findElement(arr, 15));
    }
}
