package com.company.hackerrank;

import java.util.*;

/**
 * Created by naresh.kapse on 21/07/16.
 *
 */
public class GameOfStones {
    private static final String FIRST = "First";
    private static final String SECOND = "Second";

    private static String getWinner(int stones) {
        int r = stones % 7;
        return (r == 0 || r == 1) ? SECOND : FIRST;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();

        for(int i=0; i<T; i++) {
            int stones = s.nextInt();
            System.out.println(getWinner(stones));
        }
    }
}
