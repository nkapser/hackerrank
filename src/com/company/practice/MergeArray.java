package com.company.practice;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by naresh.kapse on 04/05/16.
 */
public class MergeArray {

    public ArrayList<Integer> merge(ArrayList<Integer> first, ArrayList<Integer> second) {
        ArrayList<Integer> finalArray = new ArrayList<Integer>();
        int i = 0;
        int j = 0;
        while(i < first.size() && j < second.size()) {
            if(first.get(i) > second.get(j)) {
                finalArray.add(second.get(j));
                j++;
            }else {
                finalArray.add(first.get(i));
                i++;
            }
        }

        if(i <= first.size()-1){
            for (int k=i;k<first.size();k++) {
                finalArray.add(first.get(k));
            }
        }

        if(j <= second.size()-1){
            for (int k=j;k<second.size();k++) {
                finalArray.add(second.get(k));
            }
        }

        return finalArray;
    }

    public static void main(String[] args) {
        MergeArray ma = new MergeArray();

        ArrayList<Integer> output = ma.merge(new ArrayList<Integer>(Arrays.asList(1,4,77,99)), new ArrayList<Integer>(Arrays.asList(5,55,87)));
        System.out.println(output);
    }
}
