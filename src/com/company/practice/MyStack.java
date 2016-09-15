package com.company.practice;

/**
 * Created by naresh.kapse on 2/29/16.
 */
public class MyStack<T> {

    class Node {
        T data;
        Node next;
    }

    private int size;

    private Node top;

    public T pop() {
        if (top != null) {
            --size;
            T data = top.data;
            top = top.next;
            return data;
        }
        return null;
    }

    public T peek() {
        return top.data;
    }

    public void push(T item) {
        ++size;
        Node n = new Node();
        n.data = item;
        n.next = top;
        top = n;
    }

    private int size() {
        return size;
    }
}
