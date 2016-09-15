package com.company.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by naresh.kapse on 04/06/16.
 *
 * https://www.hackerearth.com/amazon-hiring-challenge/algorithm/akash-and-gcd-1-15/
 *
 */


class TestClass {

    private final double DENOMINATOR = Math.pow(10,9) + 7;
    private HashMap<String, Integer> gcds = new HashMap<>(); // memoize the gcd
    private HashMap<Integer, Long> fs = new HashMap<>();
    private int[] arr = new int[]{};

    class Query {
        char cmd;
        int x;
        int y;

        public Query(char cmd, int x, int y) {
            this.cmd = cmd;
            this.x = x;
            this.y = y;
        }

        public boolean isCompute() {
            return cmd == 'C';
        }

        public void execute() {
            if (isCompute()){
                System.out.println(compute(x, y));
            }else {
                update(x, y);
            }
        }
    }

    private int gcd(int a, int b) {
        String key = a+"-"+b;
        if (gcds.containsKey(key)) {
            return gcds.get(key);
        }

        int k = Math.max(a,b);
        int m = Math.min(a,b);

        while (m != 0) {
            int r = k % m;
            k = m;
            m = r;
        }

        gcds.put(key, k);
        return k;
    }

    private long computeF(int x) {
        if (fs.containsKey(x)) {
            return fs.get(x);
        }

        long result = 0;
        for (int i = 1; i <= x; i++) {
            result = result + gcd(i,x);
        }

        fs.put(x, result);

        return result;
    }

    private long compute(int x, int y) {
        double result = 0;

        for(int i=x; i <= y; i++) {
            result = result + computeF(arr[i-1]);
        }

        result = result % DENOMINATOR;
        return (long)result;
    }

    private void update(int x, int y) {
            arr[x-1] = y;
    }

    public void processQueries(int[] arr, List<Query> queries) {
        this.arr = arr;
        for(Query query : queries) {
            query.execute();
        }
    }

    public Query makeQuery(char c, int x, int y) {
        return new Query(c, x, y);
    }

    public static void main(String args[] ) throws Exception {
        /*
         * Read input from stdin and provide input before running
         */

        TestClass tc = new TestClass();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        int sizeOfArr = Integer.parseInt(line);

        int[] arr = new int[sizeOfArr];
        line = br.readLine();
        String[] values = line.split(" ");
        for (int i = 0; i < values.length; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }

        line = br.readLine();
        int noOfQueries = Integer.parseInt(line);
        List<Query> queries = new ArrayList<>();
        for(int i=0; i < noOfQueries; i++) {
            String[] l = br.readLine().split(" ");
            queries.add(tc.makeQuery(l[0].charAt(0), Integer.valueOf(l[1]), Integer.valueOf(l[2])));
        }

        tc.processQueries(arr, queries);
    }
}
