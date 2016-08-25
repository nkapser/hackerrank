package com.company.hackerrank;

/**
 * Created by naresh.kapse on 06/07/16.
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class EqualStacks {

    static class CStack {
        int height;
        int[] stack;
        int size;

        CStack(int height, int[] stack, int size) {
            this.height = height;
            this.stack = stack;
            this.size = size;
        }
    }

    private int getMaxHeightStack(CStack[] stacks) {
        int maxHeight = stacks[0].height;
        int stackIndex = 0;
        for(int i=1;i<stacks.length;i++) {
            if(stacks[i].height > maxHeight) {
                maxHeight = stacks[i].height;
                stackIndex = i;
            }
        }

        return stackIndex;
    }

    public boolean areEqual(CStack[] stacks) {
        if(stacks[0].height == stacks[1].height && stacks[1].height == stacks[2].height) {
            return true;
        }
        return false;
    }

    public int getMaxHeight(CStack[] stacks, int maxStackIndex) {
        if(areEqual(stacks)) {
            return stacks[0].height;
        }

        boolean allEqualHeight = false;
        while(!allEqualHeight) {
            CStack mstack = stacks[maxStackIndex];
            int newHeight = mstack.height - mstack.stack[mstack.size-1];
            mstack.size = mstack.size - 1;
            mstack.height = newHeight;

            maxStackIndex = getMaxHeightStack(stacks);
            allEqualHeight = areEqual(stacks);
        }

        return stacks[0].height;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int n3 = in.nextInt();
        int h1[] = new int[n1];
        int sumStack = 0;
        for(int h1_i=n1-1; h1_i >= 0; h1_i--){
            h1[h1_i] = in.nextInt();
            sumStack = sumStack + h1[h1_i];
        }
        CStack s1 = new CStack(sumStack, h1, n1);

        int h2[] = new int[n2];
        sumStack = 0;
        for(int h2_i=n2-1; h2_i >= 0; h2_i--){
            h2[h2_i] = in.nextInt();
            sumStack = sumStack + h2[h2_i];
        }
        CStack s2 = new CStack(sumStack, h2, n2);

        int h3[] = new int[n3];
        sumStack = 0;
        for(int h3_i=n3-1; h3_i >= 0; h3_i--){
            h3[h3_i] = in.nextInt();
            sumStack = sumStack + h3[h3_i];
        }
        CStack s3 = new CStack(sumStack, h3, n3);

        CStack[] stacks = new CStack[3];
        stacks[0] = s1;
        stacks[1] = s2;
        stacks[2] = s3;

//        Solution s = new Solution();
        EqualStacks s = new EqualStacks();
        int stackIndex = s.getMaxHeightStack(stacks);
        System.out.println(s.getMaxHeight(stacks,stackIndex));
    }
}


