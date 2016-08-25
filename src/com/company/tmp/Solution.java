package com.company.tmp;

/**
 * Created by naresh.kapse on 01/08/16.
 */
import java.io.*;
import java.util.*;

public class Solution {
    private static InputReader in;
    private static PrintWriter out;

    public static int[] par;
    public static int[] sz;

    public static int find(int x) {
        return par[x] == x ? x : (par[x] = find(par[x]));
    }
    public static boolean join(int a1, int a2) {
        int x = find(a1), y = find(a2);
        if (x == y) return false;
        if (sz[x] < sz[y]) {int t = x; x = y; y = t;}
        sz[x] += sz[y];
        par[y] = x;
        return true;
    }

    static class Edge implements Comparable<Edge> {
        public int from,to,weight;
        public Edge(int from,int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge other) {
            return weight-other.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out, true);

        int n = in.nextInt(), m = in.nextInt();
        par = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
            sz[i] = 1;
        }
        Edge[] e = new Edge[m];
        for (int i = 0; i < m; i++) {
            int a = in.nextInt()-1, b = in.nextInt()-1, c = in.nextInt();
            e[i] = new Edge(a,b,c);
        }
        Arrays.sort(e);
        long[] ret = new long[5*m];
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (join(e[i].from, e[i].to)) {
                graph[e[i].from].add(new Edge(-1, e[i].to, e[i].weight));
                graph[e[i].to].add(new Edge(-1, e[i].from, e[i].weight));
            }
        }
        int[] queue = new int[n];
        int[] parlen = new int[n];
        int back = 0;
        boolean[] vis = new boolean[n];
        queue[back++] = 0;
        vis[0] = true;
        for (int i = 0; i < n; i++) {
            int cur = queue[i];
            for (Edge next : graph[cur]) {
                if (vis[next.to]) continue;
                queue[back++] = next.to;
                parlen[next.to] = next.weight;
                vis[next.to] = true;
            }
        }

        int[] size = new int[n];
        for (int i = n-1; i >= 0; i--) {
            int cur = queue[i];
            size[cur]++;
            for (Edge next : graph[cur]) {
                size[cur] += size[next.to];
            }
        }

        for (int i = 1; i < n; i++) {
            ret[parlen[i]] = (long)size[i] * (long)(n-size[i]);
        }

        int last = 0;
        for (int i = 0; i+1 < ret.length; i++) {
            if (ret[i] > 0) last = i;
            ret[i+1] += ret[i]/2;
            ret[i] &= 1;
        }
        for (int i = last; i >= 0; i--) {
            out.print(ret[i]);
        }
        out.println();

        out.close();
        System.exit(0);
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }


}
