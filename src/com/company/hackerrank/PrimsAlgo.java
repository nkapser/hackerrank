package com.company.hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by naresh.kapse on 17/06/16.
 * https://www.hackerrank.com/challenges/primsmstsub
 */
public class PrimsAlgo {
    class Edge {
        int v;
        int w;
        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    class Graph {
        private LinkedList<Edge>[] graph;
        private int totalNodes;
        private int[] weights;

        Graph(int n) {
            graph = new LinkedList[n+1];
            weights = new int[n+1];
            this.totalNodes = n;
        }

        public void addEdge(int u, int v, int w) {
            if (graph[u] == null) {
                graph[u] = new LinkedList<>();
            }

            if (graph[v] == null) {
                graph[v] = new LinkedList<>();
            }

            graph[u].add(new Edge(v,w));
            graph[v].add(new Edge(u,w));
        }

        public int getTotalWeightOfTree(int src) {
            int totalWeight = 0;

            for (int i = 1; i <= totalNodes; i++) {
                weights[i] = Integer.MAX_VALUE;
            }

            boolean[] hasVisited = new boolean[totalNodes+1];
            int u = src;
            weights[u] = 0;

            for (int i = 0; i < totalNodes; i++) {
                LinkedList<Edge> edges = graph[u];
                for (Edge e: edges) {
                    if (!hasVisited[e.v] && weights[e.v] > e.w) {
                        weights[e.v] = e.w;
                    }
                }
                hasVisited[u] = true;
                // go through all unvisited nodes and get with least weight
                int min = Integer.MAX_VALUE;
                for (int k = 1; k <= totalNodes; k++) {
                    if (!hasVisited[k]){
                        if (weights[k] < min) {
                            min = weights[k];
                            u = k;
                        }
                    }
                }
            }


            for (int i = 1; i <= totalNodes; i++) {
                totalWeight += weights[i];
            }

            return totalWeight;
        }
    }

    public Graph createGraph(int n) {
        return new Graph(n);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] N_M = br.readLine().split(" ");
        int n = Integer.valueOf(N_M[0]);
        int m = Integer.valueOf(N_M[1]);

        PrimsAlgo pa = new PrimsAlgo();
        Graph g = pa.createGraph(n);

        for (int i = 0; i < m; i++) {
            String[] uvw = br.readLine().split(" ");
            g.addEdge(Integer.valueOf(uvw[0]), Integer.valueOf(uvw[1]), Integer.valueOf(uvw[2]));
        }

        int src = Integer.valueOf(br.readLine());
        System.out.println(g.getTotalWeightOfTree(src));
    }
}
