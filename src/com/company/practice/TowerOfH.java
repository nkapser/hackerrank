package com.company.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by naresh.kapse on 2/29/16.
 */
public class TowerOfH {

    public static void main(String[] args) throws Exception {

//        int i = 100000000;
//        long j = 100000000;
//
//        System.out.println(i);
//        System.out.println(j);
//        System.out.println((int)(i+j));
//        System.out.println((long)(i+j));
//
//        System.out.println("---");
//
//        int k = (int)Math.pow(10,9) + 7;
//        long l = (long)Math.pow(10,9) + 7;
//        double m = (double) Math.pow(10,9) + 7;
//
//        System.out.println(k);
//        System.out.println(l);
//        System.out.println(m);


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        String[] l = br.readLine().split(" ");
        int sum = 0;
        for(int i=0;i<l.length;i++){
            sum = sum + Integer.valueOf(l[i]);
        }


        if(sum == 2*(N-1)){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }
    }

    class Tower {
        int id;
        MyStack<Integer> stack;

        Tower(int id) {
            this.id = id;
            this.stack = new MyStack();
        }

//        public addDisk(int diskNum) {
//            stack.push(diskNum);
//        }

        private void moveDisks(int diskNum, Tower dest, Tower buff) {
            System.out.println("Moving " + diskNum + " From Tower" + this.id + " To " + dest.id);
            dest.stack.push(this.stack.pop());

            moveDisks(diskNum - 1, buff, dest);
        }
    }
}
