package com.company;

/**
 * Created by naresh.kapse on 11/06/16.
 */
public class MinEdits {

    // O(n^2)
    // Convert from s1 to s2;
    public int minDistance(String s1, String s2) {

        if (s1.equals(s2)) {
            return 0;
        }

        int[][] matrix = new int[s1.length()+1][s2.length()+1];

        int i = 0;
        int j = 0;
        // if both strings are equal.
        for (i = 0; i <= s1.length(); i++) {
            for (j = 0; j <= s2.length(); j++) {
                if (i==0){
                    matrix[i][j] = j;
                }else if (j==0) {
                    matrix[i][j] = i;
                }else if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    matrix[i][j] = matrix[i-1][j-1]; // equal
                }else {
                    matrix[i][j] = Math.min(matrix[i-1][j-1], Math.min(matrix[i][j-1], matrix[i-1][j])) + 1; // substitute
                }
            }
        }

        return matrix[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        MinEdits me = new MinEdits();
        System.out.println(me.minDistance("abcde", "abfe"));
    }
}
