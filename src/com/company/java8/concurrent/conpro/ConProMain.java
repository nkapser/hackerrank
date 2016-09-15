package com.company.java8.concurrent.conpro;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by naresh.kapse on 26/08/16.
 */
public class ConProMain {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));
        producer.start();
        consumer.start();
    }

    static class Producer  implements Runnable{

        private final Queue<String> queue;
        Producer(Queue<String> queue){
            this.queue = queue;
        }

        public void produce(String t) {
            queue.add(t);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                String v = "String:" + (i+1);
                String str = "Adding: " + v;
                System.out.println(str);
                produce(v);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        private final Queue<String> queue;
        Consumer(Queue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                if (queue.size() > 0)
                    System.out.println("Consumed: " +queue.poll());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
