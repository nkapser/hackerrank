package com.company.practice;

/**
 * Created by naresh.kapse on 01/07/16.
 */
public class FloodFill {

    public static void floodFill(char[][] mat, int x, int y, char prevC, char newC) {
        if (x < 0 || y < 0 || x >= mat.length || y >= mat[mat.length-1].length) {
            return;
        }

        if (mat[x][y] != prevC) {
            return;
        }

        mat[x][y] = newC;

        floodFill(mat, x-1, y, prevC, newC);
        floodFill(mat, x+1, y, prevC, newC);
        floodFill(mat, x, y-1, prevC, newC);
        floodFill(mat, x, y+1, prevC, newC);
    }

    public static char[][] fill(char[][] in, int r, int c) {

        printMatrix(in, c, r);

        // Step1: replace all 'O' with '-'
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (in[i][j] == 'O') {
                    in[i][j] = '-';
                }
            }
        }

        // Step2: Flood fill '-' with 'O'
        for (int i = 0; i < c; i++) {
            if (in[0][i] == '-')
                floodFill(in, 0, i, '-', 'O');
        }
        for (int i = 0; i < c; i++) {
            if (in[i][c-1] == '-')
                floodFill(in, i, c-1, '-', 'O');
        }
        for (int i = 0; i < r; i++) {
            if (in[i][0] == '-')
                floodFill(in, i, 0, '-', 'O');
        }
        for (int i = 0; i < r; i++) {
            if (in[r-1][i] == '-')
                floodFill(in, r-1, i, '-', 'O');
        }

        // Step3: replace all '-' with 'x'
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (in[i][j] == '-') {
                    in[i][j] = 'X';
                }
            }
        }
        System.out.println("------");
        printMatrix(in, c, r);

        return in;
    }

    private static void printMatrix(char[][] mat, int c, int r) {
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char mat[][] =  {
                {'X', 'O', 'X', 'O', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'O', 'X'},
                {'X', 'X', 'O', 'O', 'X', 'X'},
                {'O', 'X', 'O', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'O', 'X', 'O'},
                {'O', 'O', 'X', 'O', 'O', 'O'},
            };

        fill(mat, mat.length, mat[mat.length-1].length);
    }
}
