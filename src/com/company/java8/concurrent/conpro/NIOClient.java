package com.company.java8.concurrent.conpro;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by naresh.kapse on 26/08/16.
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        ExecutorService e = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++) {
            e.execute(new RandClient("i"+(i+1)));
        }
        e.shutdown();
    }

    static class RandClient implements Runnable {

        private String data;
        RandClient(String data) {
            this.data = data;
        }

        @Override
        public void run() {
            InetSocketAddress address = new InetSocketAddress("localhost", 9090);
            SocketChannel client = null;
            try {
                client = SocketChannel.open(address);
                client.configureBlocking(false);

                ByteBuffer buf = ByteBuffer.wrap("randomBlah".getBytes());
                client.write(buf);
                System.out.println("Sending data from: " + this.data);
                buf.clear();

                client.read(buf);
                System.out.println("Read data from: " + this.data);
                buf.flip();
                for (int i = 0; i < buf.limit(); i++) {
                    System.out.print((char) buf.get(i));
                }
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
