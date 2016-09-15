package com.company.practice;

/**
 * Created by naresh.kapse on 26/05/16.
 */
public class LCS {
    public int getLengthOfLongestSubsequence(String s1, String s2) {
        char[] s1chars = s1.toCharArray();
        char[] s2chars = s2.toCharArray();

        int[][] c = new int[s1chars.length+1][s2chars.length+1];
        for (int i = 0; i < s1chars.length ; i++) {
            c[i][0] = 0;
        }
        for (int j = 0; j < s2chars.length; j++) {
            c[0][j] = 0;
        }


        for (int i = 1; i <= s1chars.length ; i++) {
            for (int j = 1; j <= s2chars.length; j++) {
                if (s1chars[i-1] == s2chars[j-1]) {
                    c[i][j] = c[i-1][j-1] + 1;
                }else {
                    c[i][j] = Math.max(c[i-1][j], c[i][j-1]);
                }
            }
        }

        return c[s1chars.length][s2chars.length];
    }

    public static void main(String[] args) {
        LCS lcs = new LCS();
        System.out.println(lcs.getLengthOfLongestSubsequence("ABCDGH", "AEDFHR"));
    }
}
