package com.company.algos;

/**
 * Created by naresh.kapse on 09/09/16.
 */
public class SubMatrixSum {
    private final int[][] m;
    private int[][] t;
    SubMatrixSum(int[][] matrix) {
        this.m = matrix;
        preCompute();
    }

    public void preCompute() {
        int x = m.length;
        int y = m[0].length;

        this.t = new int[x+1][y+1];
        for (int i = 0; i < x; i++) {
            t[i][0] = 0;
        }
        for (int j = 0; j < y; j++) {
            t[0][j] = 0;
        }

        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                if (i == 1) {
                    t[i][j] = t[i][j-1] + m[i-1][j-1];
                }else if (j == 1) {
                    t[i][j] = t[i-1][j] + m[i-1][j-1];
                }else {
                    t[i][j] = m[i-1][j-1] + t[i-1][j] + t[i][j-1] - t[i-1][j-1];
                }
            }
        }
    }

    /*
       x,y -> refer to the top-left co-ordinates
       x1,y1 -> refer to the bottom-right co-ordinates
     */
    public int getSubMatrixSum(int x, int y, int x1, int y1) {
        if (!validCoordintes(x,y,x1,y1)) {
            System.out.println("Not a valid sub-matrix");
            return 0;
        }
        return (t[x1][y1] - t[x-1][y1] - t[x1][y-1]) + t[x-1][y-1];
    }

    private boolean validCoordintes(int x, int y, int x1, int y1) {
        if (x<=0 || y<=0 || x1>=t.length || y1>=t[0].length) {
            return false;
        }
        return true;
    }

    private void printMatrix(int[][] matrix) {
        int x = matrix.length;
        int y = matrix[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 1, 2, 3},
                {2, 1, 3, 1},
                {1, 2, 1, 1},
                {1, 1, 2, 2},
        };
        SubMatrixSum sms = new SubMatrixSum(matrix);
        // considering start index of matrix as 1;
        // 2,2,3,3 refers to
        // [1,3]
        // [2,1]
        try {
            assertTrue(7, sms.getSubMatrixSum(2,2,3,3));
            assertTrue(6, sms.getSubMatrixSum(3,3,4,4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void assertTrue(int expectedValue, int responseValue) throws Exception {
        if (expectedValue != responseValue) {
            throw new Exception("Test Failed> Expected:"+expectedValue + " but got:"+responseValue);
        }
    }
}
