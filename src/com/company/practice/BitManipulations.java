package com.company.practice;

/**
 * Created by naresh.kapse on 10/06/16.
 */
public class BitManipulations {

    /*
    AND, OR, XOR, NOT and bit shifting

    AND &
    OR |
    XOR ^
    NOT ~
     */

    public static void main(String[] args) {
        int a = 2;
        int b = 2;

        // set a bit
        int c = 1;    // 001
        c |= (c<<2);  // 101
        System.out.println(c);
        System.out.println(Integer.toBinaryString(c));

        // clear a bit
        c = 5;    // 101
        c &= ~(1<<2);  // 001
        System.out.println(c);
        System.out.println(Integer.toBinaryString(c));

        // Toggle a bit
        c = 5;         // 101
        c ^= (1<<2);  // 001
        System.out.println(c);
        System.out.println(Integer.toBinaryString(c));


//        System.out.println(a&b);
//        System.out.println(a&1);
    }

    int updateBit(int num, int i, int v) {
        int mask=~(1 << i);
        return (num&mask) | (v << i);
    }


}
