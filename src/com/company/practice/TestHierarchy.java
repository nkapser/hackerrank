package com.company.practice;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by naresh.kapse on 27/05/16.
 */
public class TestHierarchy {

    class A extends B{
        public void m1() {
            System.out.println("I am in A");
        }
    }

    class B extends C {
        public void m1() {
            System.out.println("I am in B");
        }
    }

    class C{
        public void m1() {
            System.out.println("I am in C");
        }
    }

    public void trynow(){
        A a = new A();
        a.m1();

        C a1 = (C)new B();
        a1.m1();

        B a2 = (B)new A();
        a2.m1();
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        Queue<Integer> q = new LinkedList<>();
        Deque<Integer> dq = new LinkedList<>();
        new TestHierarchy().trynow();
    }
}
