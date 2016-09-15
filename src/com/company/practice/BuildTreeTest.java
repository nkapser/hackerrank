package com.company.practice;

/**
 * Created by naresh.kapse on 02/06/16.
 */
public class BuildTreeTest {

    class Node {
        Node left;
        Node right;
        int value;

        Node(int value) {
            this.value = value;
        }
    }

    public Node build(int[] inOrder, int[] levelOrder) {
        return buildTree(inOrder, levelOrder, 0, inOrder.length-1);
    }

    private Node buildTree(int[] inOrder, int[] levelOrder, int startIndex, int endIndex) {

        if (startIndex == endIndex) {
            return new Node(inOrder[startIndex]);
        }

        int index = getIndex(inOrder, levelOrder, startIndex, endIndex);
        Node n = new Node(inOrder[index]);
        n.left = buildTree(inOrder, levelOrder, startIndex, index-1);
        n.right = buildTree(inOrder, levelOrder, index+1, endIndex);

        return n;
    }

    private int getIndex(int[] inOrder, int[] levelOrder, int startIndex, int endIndex) {

        for (int i = 0; i < levelOrder.length; i++) {
            for (int j = startIndex; j <= endIndex; j++) {
                if (levelOrder[i] == inOrder[j]) {
                    return j;
                }
            }
        }

        return -1;
    }

    public void printTree(Node n) {
        if(n != null) {
            printTree(n.left);
            System.out.println(n.value);
            printTree(n.right);
        }
    }

    public int getHeight(Node n) {
        if (n == null) {
            return 0;
        }
        return Math.max(getHeight(n.left), getHeight(n.right)) + 1;
    }

    public static void main(String[] args) {
        BuildTreeTest bt = new BuildTreeTest();
        int[] in_order = new int[]{8, 1, 11, 4, 2, 10, 7, 6, 9};
        int[] level_order = new int[]{10, 4, 6, 1, 2, 7, 9, 8, 11};

        Node n = bt.build(in_order, level_order);

        bt.printTree(n);

        System.out.println("height: " + bt.getHeight(n));
    }
}
