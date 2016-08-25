package com.company.oldserverclient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by naresh.kapse on 16/08/16.
 */
public class NewIOServer {
    private static Map<SocketChannel, Queue<ByteBuffer>> pendingData = new HashMap<>();

//    private static Collection<SocketChannel> sockets = Collections.newSetFromMap(
//            new HashMap<SocketChannel, Boolean>()
//    );

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost", 9898));
        ssc.configureBlocking(false);

        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Iterator<SelectionKey> itkeys = selector.selectedKeys().iterator();
            while (itkeys.hasNext()) {
                SelectionKey key = itkeys.next();
                itkeys.remove();
                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        // someone connected to our ServerSocketChannel
                        accept(key);
                    }

                    if (key.isReadable()) {
                        read(key);
                    }

                    if (key.isWritable()) {
                        write(key);
                    }
                }

            }
        }

    }

    private static void write(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel)key.channel();
        Queue<ByteBuffer> queue = pendingData.get(sc);
        ByteBuffer buf;

        while ((buf = queue.peek()) != null) {
            sc.write(buf);
            if (!buf.hasRemaining()) {
                queue.poll();
            }else {
                return;
            }
        }
        sc.register(key.selector(), SelectionKey.OP_READ);
    }

    private static void read(SelectionKey key) throws IOException {
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);

        SocketChannel sc = (SocketChannel) key.channel();
        int bytesRead = sc.read(buf);
        if (bytesRead == -1) {
            pendingData.remove(sc);
            return;
        }

        buf.flip();

        for (int i = 0; i < buf.limit(); i++) {
            buf.put(i, (byte) Util.transmogrify(buf.get(i)));
        }
        pendingData.get(sc).add(buf);
        sc.register(key.selector(), SelectionKey.OP_WRITE);
    }

    private static void accept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept(); // never null
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ);
        pendingData.put(sc, new ConcurrentLinkedQueue<>());
    }
}
