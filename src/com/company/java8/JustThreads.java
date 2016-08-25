package com.company.java8;

import java.util.ArrayList;

/**
 * Created by naresh.kapse on 23/08/16.
 */
public class JustThreads {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 150; i++) {
            Thread t = new Thread("Name"+(i+1));
            threads.add(t);
            t.start();
        }

        Thread.sleep(100000000);
    }
}
