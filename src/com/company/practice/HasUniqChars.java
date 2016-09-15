package com.company.practice;

import java.util.HashSet;

public class HasUniqChars {

    /*

    1.1 Implement an algorithm to determine if a string has all unique characters.
    What if you cannot use additional data structures?
    */

    private boolean hasAllUniqueChars(String str) {
        HashSet<Character> characters = new HashSet<>();
        int len = str.length();
        for (int i = 0; i<len; i++) {
            if (characters.contains(str.charAt(i))) {
                return false;
            }else {
                characters.add(str.charAt(i));
            }
        }

        return true;
    }

    private boolean hasAllUniqChars2(String str) {
        int checker = 0;
        for(int i=0;i<str.length();i++) {
            int val = str.charAt(i) - 'a';
            if((checker & (1 << val)) > 0) return false;
            checker |= (1 << val);
        }

        return true;
    }

    public static void main(String[] args) {
        HasUniqChars m = new HasUniqChars();
        String[] words = {"abcde", "hello", "apple", "kite", "padle"};
        for (String word : words) {
            System.out.println(word + ": " + m.hasAllUniqueChars(word) + " " + m.hasAllUniqChars2(word));
        }
    }
}
