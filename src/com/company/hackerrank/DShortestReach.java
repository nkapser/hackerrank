package com.company.hackerrank;

import java.util.*;

/**
 * Created by naresh.kapse on 29/07/16.
 */
public class DShortestReach {
    static class Node implements Comparable<Node>{
        int n;
        int w;
        Node(int n, int w) {
            this.n = n;
            this.w = w;
        }


        @Override
        public String toString() {
            return n + " : " + w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static class Graph {
        private int size;
        private LinkedList<Node>[] edges;
        Graph(int size) {
            this.size = size;
            edges = new LinkedList[size+1];
        }

        // from, to, weight
        public void addEdge(int f, int t, int w) {
            if (edges[f] == null) {
                edges[f] = new LinkedList<>();
            }

            if (edges[t] == null) {
                edges[t] = new LinkedList<>();
            }

            edges[f].add(new Node(t,w));
            edges[t].add(new Node(f,w));
        }

        // source
        public void printShortestPathsFrom(int s) {
            int[] wn = new int[size+1]; // weights at nodes
            for (int i = 1; i <= size; i++) {
                wn[i] = Integer.MAX_VALUE;
            }
            wn[s] = 0;

            // bfs
            PriorityQueue<Node> q = new PriorityQueue<>();
            q.add(new Node(s,0));

            while (q.size() > 0 ) {
                Node f = q.peek(); q.remove();

                LinkedList<Node> edgeNodes = edges[f.n];
                for (Node node:edgeNodes) {

                    if (wn[f.n] + node.w < wn[node.n]) {
                        wn[node.n] = wn[f.n] + node.w;
                        q.add(new Node(node.n, wn[f.n] + node.w));
                    }
                }
            }

            for (int i = 1; i <= size ; i++) {
                if (i != s) {
                    System.out.print(wn[i] == Integer.MAX_VALUE ? "-1 " : wn[i]+" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-->0) {
            int N = s.nextInt();
            int M = s.nextInt();

            Graph g = new Graph(N);
            for (int i = 0; i < M; i++) {
                int f = s.nextInt();
                int t = s.nextInt();
                int w = s.nextInt();
                g.addEdge(f,t,w);
            }

            int root = s.nextInt();

            g.printShortestPathsFrom(root);
        }
    }
}
