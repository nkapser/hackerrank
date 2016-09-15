package com.company.practice;


 /*
Given in order and level order traversals for a binary tree, construct the original tree.
            10
       4          6
    1     2    7     9
 8    11

 in-order = {8, 1, 11, 4, 2, 10, 7, 6, 9}
 level-order = {10, 4, 6, 1, 2, 7, 9, 8, 11}

  10

4    6

in-order = {4,10,6}
level-order = {10,4,6}


  (8,1,11,4,2)    10   (7,6,9)
(8, 1, 11) 4 (2)        (7)  6 (9)
(8) 1 (11)

 */

public class BuildTreetmp {

class Node {
    Node left;
    Node right;
    Node parent;
    int value;

    Node(int value) {
        this.value = value;
    }
}

    private Node root = null;

    // divide and conquer, recursive
    public Node build(int[] inOrder, int[] levelOrder) {
        if(levelOrder.length == 0) {
            return null;
        }

        int rootElement = levelOrder[0];
        levelOrder = getLevelOrder(inOrder, levelOrder);

        return buildSubTree(rootElement, inOrder, levelOrder);
    }

    /*
        getLevelOrder - O(n^2) || can be reduced!
        if we put levelOrder elements in the LinkedHashSet
        then it will reduce to O(n)
    */
    private int[] getLevelOrder(int[] elements, int[] levelOrder) {
        int index = -1;

        for(int i=0;i<levelOrder.length;i++){
            for(int j=0;j<elements.length;j++) {
                if(levelOrder[i] == elements[j]) {
                    index = i;
                }
            }
        }

        int[] elements1 = new int[levelOrder.length-index];

        for (int i = index+1; i < levelOrder.length; i++) {
            elements1[i] = levelOrder[i];
        }
        return elements1;
    }

    private int[] getLeftElements(int root,int[] inOrder) {
        int index = getIndex(root, inOrder);
        int[] elements = new int[index];
        for(int i = 0; i < index; i++){
            elements[i] = inOrder[i];
        }
        return elements;
    }

    private int[] getRightElements(int root,int[] inOrder) {
        int index = getIndex(root, inOrder);
        int[] elements = new int[inOrder.length - index];
        for(int i = index+1; i < inOrder.length; i++){
            elements[i] = inOrder[i];
        }
        return elements;
    }

    private int getIndex(int element, int[] array) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if(array[i] == element) {
                index = i;
            }
        }
        return index;
    }

    private int getRoot(int[] elements, int[] levelOrder) {
        for(int i=0;i<levelOrder.length;i++) {
            for(int j=0;j<elements.length;j++) {
                if(elements[j] == levelOrder[i]) {
                    return levelOrder[i];
                }
            }
        }
        return -1;
    }

    /*
        Overall Time Complexity: O(N^2)
    */
    public Node buildSubTree(int root, int[] inOrder, int[] levelOrder) {
        int[] leftElements = getLeftElements(root, inOrder);
        int[] rightElements = getRightElements(root, inOrder);

        Node n = new Node(root);

        int[] getLeftLevelOrder = getLevelOrder(leftElements, levelOrder);
        int getLeftRoot = getRoot(leftElements, getLeftLevelOrder);
        Node l  = buildSubTree(getLeftRoot, leftElements, getLeftLevelOrder);

        int[] getRightLevelOrder = getLevelOrder(rightElements, levelOrder);
        int getRightRoot = getRoot(rightElements, getRightLevelOrder);
        Node r = buildSubTree(getRightRoot, rightElements, getRightLevelOrder);

        l.parent = n;
        r.parent = n;

        return n;
    }

    public void printTree(Node n) {
        if (n!=null) {
            printTree(n.left);
            System.out.println(n.value);
            printTree(n.right);
        }
    }

    public static void main(String[] args) {
        BuildTreetmp bt = new BuildTreetmp();
        int[] in_order = new int[]{8, 1, 11, 4, 2, 10, 7, 6, 9};
        int[] level_order = new int[]{10, 4, 6, 1, 2, 7, 9, 8, 11};

        Node n = bt.build(in_order, level_order);

        bt.printTree(n);
    }
}
