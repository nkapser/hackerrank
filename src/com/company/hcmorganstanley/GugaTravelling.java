package com.company.hcmorganstanley;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by naresh.kapse on 06/08/16.
 */
public class GugaTravelling {

    enum PathType { SPECIAL, NON_SPECIAL };

    class Node {
        private int n;
        private int time;
        private PathType pathType;
        Node(int n, int time, PathType pathType) {
            this.n = n;
            this.time = time;
            this.pathType = pathType;
        }

        @Override
        public String toString() {
            return "N: " + n + " | time: " + time + " | " + pathType;
        }
    }

    class PathTypeCompartor implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.pathType.compareTo(o2.pathType);
        }
    }

    class TimeCompartor implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return Integer.compare(o1.time, o2.time);
        }
    }

    class MixAndMatchComparator implements Comparator<Node> {
        List<Comparator<Node>> comparators;

        public MixAndMatchComparator(List<Comparator<Node>> comparators) {
            this.comparators=comparators;
        }

        @Override
        public int compare(Node o1, Node o2) {
            int compareResult = 0;
            for(Comparator<Node> comparator : comparators) {
                if(comparator.compare(o1, o2)!=0) {
                    return comparator.compare(o1, o2);
                }
            }
            return compareResult;
        }
    }

    class Graph {
        private final int size;
        private LinkedList<Node>[] edges;

        Graph (int size) {
            this.size = size;
            edges = new LinkedList[size+1];
        }

        public void addEdge(int from, int to, int time, PathType pathType) {
            if (edges[from] == null) {
                edges[from] = new LinkedList<Node>();
            }

            if (edges[to] == null) {
                edges[to] = new LinkedList<>();
            }

            edges[from].add(new Node(to, time, pathType));
            edges[to].add(new Node(from, time, pathType));
        }

        public int getMinTime() {
            List<Comparator<Node>> comparators = new ArrayList<Comparator<Node>>();
//            comparators.add(new PathTypeCompartor());
            comparators.add(new TimeCompartor());
            MixAndMatchComparator orComparator = new MixAndMatchComparator(comparators);

            PriorityQueue<Node> q = new PriorityQueue<>(orComparator);

            int[] vw = new int[size+1];
            int[] paths = new int[size+1];
            for (int i = 1; i <= size ; i++) {
                vw[i] = Integer.MAX_VALUE;
            }
            vw[1] = 0;
            q.add(new Node(1,0,PathType.NON_SPECIAL)); // dummy

            while (q.size() > 0) {
                Node s = q.peek(); q.remove();
                System.out.println(s);
                LinkedList<Node> edgeNodes = edges[s.n];
                Iterator<Node> it = edgeNodes.iterator();
                while(it.hasNext()) {
                    Node d = it.next();
                    if (d.time + vw[s.n] < vw[d.n]) {
                        int cost = d.time + vw[s.n];
                        vw[d.n] = cost;
                        paths[d.n] = s.n;
                        q.add(new Node(d.n, cost, d.pathType));
                    }
                }
            }

            System.out.println(Arrays.toString(vw));
            System.out.println(Arrays.toString(paths));
            return -1;
        }
    }

    public Graph createGraph(int N) {
        return new Graph(N);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int M = s.nextInt();
        int K = s.nextInt();

        Graph g = new GugaTravelling().createGraph(N);
        int totalEdges = M+K;
        for (int i = 1; i <= totalEdges; i++) {
            int from = s.nextInt();
            int to = s.nextInt();
            int time = s.nextInt();
            if (i <= M) {
                g.addEdge(from, to, time, PathType.NON_SPECIAL);
            }else {
                g.addEdge(from, to, time, PathType.SPECIAL);
            }
        }

        System.out.println(g.getMinTime());
    }
}
