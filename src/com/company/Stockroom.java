package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Stockroom {

    class WordWithEditCounts implements Comparable{
        String string;
        Integer requiredEdits;

        WordWithEditCounts(int requiredEdits, String string) {
            this.requiredEdits = requiredEdits;
            this.string = string;
        }

        @Override
        public int compareTo(Object o) {
            WordWithEditCounts w = (WordWithEditCounts)o;
            if (requiredEdits < w.requiredEdits && string.compareTo(w.string) < 1) return -1;
            if (requiredEdits > w.requiredEdits && string.compareTo(w.string) > 1) return 1;
            if (requiredEdits == w.requiredEdits && string.compareTo(w.string) < 1) return -1;
            if (requiredEdits == w.requiredEdits && string.compareTo(w.string) > 1) return 1;
            return 0;
        }

        @Override
        public String toString() {
            return requiredEdits + ":" + string;
        }
    }

    //Dynamic programming to get minEdits required!
    /*
     *   result = min(result, w(i,j))
     */
    // O(n^2)
    // Convert from s1 to s2;
    public int minDistance(String s1, String s2) {

        if (s1.equals(s2)) {
            return 0;
        }

        int[][] matrix = new int[s1.length()+1][s2.length()+1];

        // if both strings are equal.
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
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

    public List<String> getTopApproximateMatches(String query, List<String> strings) {
        List<WordWithEditCounts> result2 = new ArrayList<>();

        for (String s:strings) {
            result2.add(new WordWithEditCounts(minDistance(query, s), s));
        }

        Collections.sort(result2);
        System.out.println(result2);

        return new ArrayList<>();
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.valueOf(br.readLine());

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            strings.add(br.readLine());
        }

        String query = br.readLine();

        Stockroom m = new Stockroom();
        m.getTopApproximateMatches(query, strings);
    }
}
