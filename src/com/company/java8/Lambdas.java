package com.company.java8;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

/**
 * Created by naresh.kapse on 26/07/16.
 */
public class Lambdas {
    private static void test1() {
        List<String> strs = Arrays.asList("apple", "carrot", "banana", "jackfruit");
        Collections.sort(strs, (a,b) -> b.compareTo(a));

        System.out.println(strs);
    }

    private static void test2() {
        Converter<String, Integer> c = (from) -> Integer.valueOf(from);
        List<String> nums = Arrays.asList("1","2","3");
        for (String s:nums) {
            System.out.println(c.convert(s));
        }

        for (String s:nums) {
            System.out.println(c.percentage(c.convert(s)));
        }
    }

    private static void test3() {


        int max = 1000000;
        List<String> uuids = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            uuids.add(UUID.randomUUID().toString());
        }

        long starTime = System.nanoTime();
        long count = uuids.stream().sorted().count();
        System.out.println("Time taken to sort: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - starTime) + " ms.");

        starTime = System.nanoTime();
        count = uuids.parallelStream().sorted().count();
        System.out.println("Time taken to sort: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - starTime) + " ms.");
    }

    public static void test4() {
        IntStream.range(1, 4)
                .forEach((s) -> System.out.println(s + " -> " + s*s));
    }

    public static void test5() {
        LongBinaryOperator op = (x,y) -> 2 * (x + y);
        LongAccumulator accumulator = new LongAccumulator(op, 1L);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1).forEach(i -> executorService.submit(() -> accumulator.accumulate(i)));

        executorService.shutdown();

        System.out.println(accumulator.getThenReset());
    }

    int count = 0;
    AtomicInteger atomicCount = new AtomicInteger(0);
    public void increment() {
        count = count + 1;
        atomicCount.incrementAndGet();
    }

    ExecutorService executor = Executors.newFixedThreadPool(10);
    public void test6() {
        IntStream.range(0, 10000).forEach((x) -> { executor.submit(this::increment); } );
        try {
            executor.shutdown();
        }catch (Exception e)  {
            try {
                Thread.sleep(2000);
                executor.shutdownNow();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("NormalCount: " + count);
        System.out.println("AtomicCount: " + atomicCount.get());
    }

    public static void main(String[] args) {
        new Lambdas().test6();
    }
}

@FunctionalInterface
interface Converter<F,T> {
    T convert(F from);
    default double percentage(double a){
        return a / 100;
    }
}
