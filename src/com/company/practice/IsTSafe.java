package com.company.practice;

/**
 * Created by naresh.kapse on 04/05/16.
 */
public class IsTSafe {
    private static int x = 10; // this variable should be static

    public int getX() {
        return x;
    }

    public synchronized void setX(int y) {
        x = y;
    }
}
