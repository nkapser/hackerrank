package com.company.hackerrank;

/**
 * Created by naresh.kapse on 20/07/16.
 */
import java.io.*;
import java.util.*;

public class Solution {
    private static InputReader in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out, true);

        int t = in.nextInt();
        while(t-->0) {
            int n = in.nextInt();
            int[] arr = new int[n+1];
            for (int i = 1; i <= n; i++) arr[i] = in.nextInt();
            Arrays.sort(arr);
            long[] sum = new long[n+1];
            for (int i = 1; i <= n; i++)
                sum[i] = sum[i-1] + arr[i];
            long all = sum[n];
            long max = 0;
            for (int i = 1; i <= n; i++) {
                max = Math.max((long)i*(all-sum[i-1]), max);
            }
            out.println(max);
        }

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