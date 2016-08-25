package com.company.hackerrank;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by naresh.kapse on 30/07/16.
 */
public class FloydCity {

    static class Node implements Comparable<Node>{
        int v;
        int w;
        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    private static class Graph{
        private HashMap<Integer, Integer>[] g;
        private int[][] storedInMemMap; // used if the source is same.
        private int size;
        Graph(int size) {
            this.size = size;
            g = new HashMap[size+1];
            storedInMemMap = new int[size+1][];
        }

        public void addEdge(int f, int t, int w) {
            if (g[f] == null){
                g[f] = new HashMap<>(size+1);
            }
            g[f].put(t, w);     // helps in replacing with latest edge
        }

        public int getMinDistance(int f, int t) {

            if (storedInMemMap[f] != null) {
                return storedInMemMap[f][t];
            }

            int[] vw = new int[size+1];
            IntStream.range(1, size+1).parallel().forEach((x) -> vw[x] = Integer.MAX_VALUE);
            vw[f] = 0; // Weight at Source

            PriorityQueue<Node> q = new PriorityQueue<>(size+1);
            q.add(new Node(f,0));

            while (q.size()>0) {
                Node s = q.peek(); q.remove();
                HashMap<Integer, Integer> vedges = g[s.v];
                if (vedges!=null) {
                    Iterator<Integer> it = vedges.keySet().iterator();
                    while(it.hasNext()) {
                        int d = it.next();
                        if (vw[s.v] + g[s.v].get(d).intValue() < vw[d]) {
                            int weight = vw[s.v] + g[s.v].get(d).intValue();
                            vw[d] = weight;
                            q.add(new Node(d, weight));
                        }
                    }
                }
            }

            for (int i = 1; i <= size; i++) {
                vw[i] = (vw[i] == Integer.MAX_VALUE) ? -1 : vw[i];
            }

            storedInMemMap[f] = vw;

            return vw[t];
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        Graph g = new Graph(N);

        int M = s.nextInt();
        while (M-- > 0) {
            int f = s.nextInt();
            int t = s.nextInt();
            int w = s.nextInt();
            g.addEdge(f,t,w);
        }

        int Q = s.nextInt();
        while (Q-- > 0) {
            int f = s.nextInt();
            int t = s.nextInt();
            System.out.println(g.getMinDistance(f,t));
        }
    }
}
