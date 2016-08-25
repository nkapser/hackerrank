package com.company.hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by naresh.kapse on 02/07/16.
 *
 * https://www.hackerrank.com/challenges/even-tree
 */
public class EvenTree {
        private LinkedList<Integer>[] graph;
        private boolean[] hasVisited;
        private int[] childCount;

        EvenTree(int n) {
            graph = new LinkedList[n + 1];
            hasVisited = new boolean[n + 1];
            childCount = new int[n+1];
        }

        public void addEdge(int u, int v) {
            if (graph[u] == null){
                graph[u] = new LinkedList<Integer>();
            }
            if (graph[v] == null){
                graph[v] = new LinkedList<Integer>();
            }

            graph[u].add(v);
            graph[v].add(u);
        }


    private boolean isStart = true;
    private int breakEdges = 0;
    public int maximumEdgesRemoved(int u) {
        int childNodes = 0;
        hasVisited[u] = true;

        if (childCount[u] > 0) {
            return childCount[u];
        }

        if (!isStart && graph[u].size() == 1) {
            return 1;
        }

        isStart = false;

        for (int v:graph[u]) {
            if(!hasVisited[v]) {
                childNodes = maximumEdgesRemoved(v);
                boolean b = false;
                int dcn = 0;
                for(int k : graph[v]) {
                    if (k != u) {
                        dcn = dcn + childCount[k];
                        b = true;
                    }
                }
                if (b) {
                    childNodes = dcn + 1;
                }
                childCount[v] = childNodes;
                if (childNodes % 2 == 0) {
                    ++breakEdges;
                }
            }
        }

        return breakEdges;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] N_M = br.readLine().split(" ");
        int N = Integer.parseInt(N_M[0]); // No of Vertices
        int M = Integer.parseInt(N_M[1]); // No of Edges

        EvenTree et = new EvenTree(N);

        for (int i = 0; i < M; i++) {
            String[] u_v = br.readLine().split(" ");
            et.addEdge(Integer.valueOf(u_v[0]), Integer.valueOf(u_v[1]));
        }

        System.out.println(et.maximumEdgesRemoved(1));
    }
}
