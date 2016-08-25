package com.company.hackerrank;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by naresh.kapse on 31/07/16.
 * https://www.hackerrank.com/challenges/johnland
 */
public class JohnLand {

    class Node implements Comparable<Node>{
        int v;
        int w;
        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.v, o.v);
        }
    }

    private ArrayList<Node>[] graph;
    private int[][] storedGraphWeights;
    private int size;

    JohnLand(int size) {
        this.size = size;
        graph = new ArrayList[size+1];
        storedGraphWeights = new int[size+1][];
    }

    private void addEdge(int s, int d, int w) {
        if (graph[s] == null) {
            graph[s] = new ArrayList<>(size+1);
        }

        if (graph[d] == null) {
            graph[d] = new ArrayList<>(size+1);
        }

        graph[s].add(new Node(d, w));
        graph[d].add(new Node(s, w));
    }

    private int getMinWeight(int s) {

//        if (storedGraphWeights[s] != null) {
//            return storedGraphWeights[s][d];
//        }

        int[] vw = new int[size+1];
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(s,0));

        for (int i = 1; i <= size; i++) {
            vw[i] = Integer.MAX_VALUE;
        }
        vw[s] = 0;

        while (q.size() > 0) {
            Node n = q.peek(); q.remove();
            ArrayList<Node> edges = graph[n.v];
            for (Node edge:edges) {
                if (vw[n.v] + edge.w < vw[edge.v]) {
                    int w = vw[n.v] + edge.w;
                    vw[edge.v] = w;
                    q.add(new Node(edge.v, w));
                }
            }
        }

        int sum = 0;
        for (int i = s; i <= size; i++) {
            sum = sum +((vw[i] == Integer.MAX_VALUE) ? 0 : vw[i]);
        }

//        storedGraphWeights[s] = vw;

        return sum;
    }

    private void printSumOfMinDistances(int N) {
        int sum = 0;
        for (int i = 1; i < N; i++) {
            sum = sum + getMinWeight(i);
        }
        BigInteger bi = BigInteger.valueOf(2).pow(70595208);
        System.out.println(bi.toString());
        System.out.println("--");
        System.out.println(bi.toString(2));
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int M = s.nextInt();

        JohnLand sol = new JohnLand(N);

        while(M-->0) {
            int f = s.nextInt();
            int t = s.nextInt();
            int w = s.nextInt();
            sol.addEdge(f,t,w);
        }
        sol.printSumOfMinDistances(N);
    }
}
