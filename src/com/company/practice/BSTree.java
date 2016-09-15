package com.company.practice;

/**
 * Created by naresh.kapse on 29/05/16.
 */
public class BSTree<T extends Comparable> {

    static class Node<T extends Comparable> {
        Node left;
        Node right;
        T value;

        Node(T v) {
            this.value = v;
        }
    }

    private Node root = null;

    public Node insert(Node n, T t) {
        if (n == null) {
            n = new Node(t);
        }else if (n.value.compareTo(t) <= 0) {
            n.left = insert(n.left, t);
        }else if (n.value.compareTo(t) > 0) {
            n.right = insert(n.right, t);
        }
        return n;
    }

    public boolean isValidBST(Node n) {
        if (n == null) {
            return true;
        }

        if (n.left != null && n.value.compareTo(n.left.value) < 0) {
            return false;
        }

        if (n.right != null && n.value.compareTo(n.right.value) > 0) {
            return false;
        }

        return isValidBST(n.left) && isValidBST(n.right);
    }

    public boolean isValidBST(Node n, Integer min, Integer max) {
        if (n == null) {
            return true;
        }

        if (n.value.compareTo(min) < 0 || n.value.compareTo(max) > 0) {
            return false;
        }

        return isValidBST(n.left, min, (Integer) n.value) && isValidBST(n.right, (Integer) n.value, max);
    }

    public void remove(T t) {
        Node x = findAndGetNode(root, t);

    }

    private Node findAndGetNode(Node n, T t) {
        if (n != null && n.value.compareTo(t) == 0) {
            return n;
        }else if (n != null && n.value.compareTo(t) < 0) {
            n = findAndGetNode(n.left, t);
        }else if (n != null && n.value.compareTo(t) > 0) {
            n = findAndGetNode(n.right, t);
        }
        return null;
    }

    public void removeMin(T t) {

    }

    public boolean contains(T t) {
        return false;
    }

    public static void main(String[] args) {
        BSTree<Integer> bst = new BSTree<>();

                /* Invalid tree */

        BSTree.Node n = new BSTree.Node(new Integer(3));
        n.left =  new BSTree.Node(new Integer(2));
        n.right =  new BSTree.Node(new Integer(5));
        n.left.left = new BSTree.Node(new Integer(1));
        n.left.right = new BSTree.Node(new Integer(4));

//        System.out.println(bst.isValidBST(n));
        System.out.println(bst.isValidBST(n, Integer.MIN_VALUE, Integer.MAX_VALUE));


                /* Valid tree */

        BSTree.Node n1 = new BSTree.Node(new Integer(4));
        n1.left =  new BSTree.Node(new Integer(2));
        n1.right =  new BSTree.Node(new Integer(5));
        n1.left.left = new BSTree.Node(new Integer(1));
        n1.left.right = new BSTree.Node(new Integer(3));

//        System.out.println(bst.isValidBST(n1));
        System.out.println(bst.isValidBST(n1, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

}
