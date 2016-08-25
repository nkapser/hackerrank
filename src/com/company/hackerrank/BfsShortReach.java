package com.company.hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by naresh.kapse on 11/06/16.
 * https://www.hackerrank.com/challenges/bfsshortreach
 */
public class BfsShortReach {

    class Graph {
        private int[] nodes;
        private ArrayList[] edges;

        Graph(int totalNodes, int totalEdges) {
            nodes = new int[totalNodes];
            edges = new ArrayList[totalNodes+1];
        }

        public void addEdge(int s, int d) {
            if (edges[s] == null) {
                edges[s] = new ArrayList();
            }
            if (edges[d] == null) {
                edges[d] = new ArrayList();
            }
            edges[s].add(d);
            edges[d].add(s);
        }

        private int calculateShortestPath(int s , int d) {

            if (edges[s] == null || edges[d] == null) {
                return -1;
            }

            boolean[] hasVisited = new boolean[nodes.length+1];
            int[] w = new int[nodes.length+1];

            Queue<Integer> queue = new LinkedList<>();
            queue.add(s);

            while (!queue.isEmpty()) {
                int u = queue.poll();
                hasVisited[u] = true;

                if (edges[u] != null) {
                    ArrayList<Integer> adjacentNodes = edges[u];
                    for (int v : adjacentNodes) {
                        if (!hasVisited[v]) {
                            if (w[v] == 0) {
                                w[v] = w[u] + 6;
                            }else {
                                w[v] = w[u] + 6 < w[v] ? w[u]+6 : w[v];
                            }
                            if (d == v) {
                                return w[v];
                            }
                            queue.add(v);
                        }
                    }
                }
            }

            return -1;
        }

        public void calulateShortestPathFrom(int s) {
            for (int i = 1; i <= nodes.length ; i++) {
                if (s != i) {
                    System.out.print(calculateShortestPath(s, i) + " ");
                }
            }
            System.out.println();
        }
    }

    public Graph createGraph(int nodes, int totalEdges){
        return new Graph(nodes, totalEdges);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BfsShortReach bsr = new BfsShortReach();

        int N = Integer.valueOf(br.readLine()); // number of test cases
        for (int i = 0; i < N; i++) {
            String[] nes = br.readLine().split(" ");
            int nodes = Integer.valueOf(nes[0]);
            int edges = Integer.valueOf(nes[1]);

            Graph g = bsr.createGraph(nodes, edges);
            for (int j = 0; j < edges; j++) {
                String[] n2n = br.readLine().split(" ");
                g.addEdge(Integer.valueOf(n2n[0]), Integer.valueOf(n2n[1]));
            }

            int startingSource = Integer.valueOf(br.readLine().trim());
            g.calulateShortestPathFrom(startingSource);
        }
    }
}
