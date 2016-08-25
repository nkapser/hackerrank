package com.company.hackerrank;

import java.util.*;

/**
 * Created by naresh.kapse on 28/07/16.
 *
 * https://www.hackerrank.com/challenges/board-cutting
 */
public class CuttingBoards {
    static class Arr implements Comparable<Arr>{
        long val;
        int horv;
        int index;

        Arr(long val, int index, int horv) {
            this.val = val;
            this.index = index;
            this.horv = horv;
        }

        @Override
        public String toString() {
            String c = "x";
            if (horv == 0) {
                c = "y";
            }
            return c + index;
        }

        @Override
        public int compareTo(Arr o) {
            if (this.val < o.val) {
                return 1;
            }else if(this.val > o.val) {
                return -1;
            }
            return 0;

        }
    }

    private static long getMinCost(List<Arr> list) {
        long cost = 0;
        int v=0;
        int h=0;
        for (Arr a:list) {
            if (a.horv == 1) {
                cost = (cost + (a.val*(v+1)) % 1000000007) % 1000000007;
                h++;
            }else {
                cost = (cost + (a.val*(h+1)) % 1000000007) % 1000000007;
                v++;
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int T = s.nextInt();
        while (T-->0) {
            int x = s.nextInt();
            int y = s.nextInt();
            int[] cx = new int[x];
            int[] cy = new int[y];
            ArrayList<Arr> list = new ArrayList<Arr>(x+y);
            for (int i = 1; i < x; i++) {
                list.add(new Arr(s.nextInt(), i,  1));
            }
            for (int i = 1; i < y; i++) {
                list.add(new Arr(s.nextInt(), i, 0));
            }

            Collections.sort(list);

            System.out.println(getMinCost(list));
        }
    }
}
