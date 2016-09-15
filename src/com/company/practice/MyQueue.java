package com.company.practice;

/**
 * Created by naresh.kapse on 2/29/16.
 */
public class MyQueue {
    class Node {
        Object data;
        Node next;
    }

    private Node first, last;

    public void enQueue(Object item) {
        Node i = new Node();
        i.data = item;

        if (first == null) {
            last = i;
            first = i;
        }else {
            last.next = i;
            last = i;
        }
    }

    public Object deQueue() {
        if (first != null) {
            Object data = first.data;
            first = first.next;
            return data;
        }
        return null;
    }
}
