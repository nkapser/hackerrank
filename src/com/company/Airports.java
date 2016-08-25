package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by naresh.kapse on 11/06/16.
 */
public class Airports {

    class Graph {

        private final int nodes;
        // Adjacent lists
        private ArrayList[] edges;

        Graph(int nodes) {
            this.nodes = nodes;
            this.edges = new ArrayList[nodes];
        }

        public void addEdge(int s, int d) {
            if (edges[s] == null) {
                edges[s] = new ArrayList();
                edges[s].add(d);
            }else {
                edges[s].add(d);
            }

            if (edges[d] == null) {
                edges[d] = new ArrayList();
                edges[d].add(s);
            }else {
                edges[d].add(s);
            }
        }

        // BFS
        public int doesPathExists(int s, int d) {

            // Reset Visited Nodes;
            boolean[] hasVisited = new boolean[nodes];

            Queue<Integer> queue = new LinkedList<>();
            queue.add(s);

            while(!queue.isEmpty()) {
                int v = queue.poll();
                hasVisited[v] = true;
                List<Integer> adjacentNodes = edges[v];

                for (Integer adjNode : adjacentNodes) {
                    if (!hasVisited[adjNode]) {
                        if(d == adjNode) {
                            return 1;
                        }
                        queue.add(adjNode);
                    }
                }
            }

            return 0;
        }
    }

    public Graph initGraph(int T) {
        return new Graph(T);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s[] = br.readLine().split(" ");
        int T = Integer.valueOf(s[0]);
        int N = Integer.valueOf(s[1]);

        Airports a = new Airports();
        Graph graph = a.initGraph(T);

        for (int i = 0; i < N; i++) {
            String[] e = br.readLine().split(" ");
            int from = Integer.valueOf(e[0]);
            int to = Integer.valueOf(e[1]);

            graph.addEdge(from, to);
        }

        int Q = Integer.valueOf(br.readLine());
        for (int i = 0; i < Q; i++) {
            String[] e = br.readLine().split(" ");
            int from = Integer.valueOf(e[0]);
            int to = Integer.valueOf(e[1]);
            System.out.println(graph.doesPathExists(from, to));
        }
    }
}
