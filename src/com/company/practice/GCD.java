package com.company.practice;

/**
 * Created by naresh.kapse on 06/06/16.
 */
public class GCD {

    private int gcdr(int a, int b) {
        int k = Math.max(a,b);
        int m = Math.min(a,b);

        int i=0;
        while (m != 0) {
            System.out.println("steps: " + ++i);
            int r = k % m;
            k = m;
            m = r;
        }

        return k;
    }

    // subsraction
    private int gcd(int a, int b) {

        int i=0;
        while(a != b) {
            while(a>b) {
                System.out.println("steps: " + ++i);
                int c = a-b;
                a = c;
            }
            while(b>a) {
                System.out.println("steps: " + ++i);
                int c = b-a;
                b = c;
            }
        }
        return a;
    }

    public void testInt(int[] arr, int x, int y){
        arr[x] = y;
    }

    public static void printArr(int[] arr, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GCD gcd = new GCD();
        System.out.println(gcd.gcd(420,96));
        System.out.println("---");
        System.out.println(gcd.gcdr(420,96));

        int[] testArr = new int[] {1,2,3,4,5};

        printArr(testArr, testArr.length);

        gcd.testInt(testArr, 0, 3);


        printArr(testArr, testArr.length);
    }
}
