package com.company.java8;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by naresh.kapse on 15/08/16.
 */
public class MyServer {
    ServerSocketChannel serverSocketChannel;
    Selector selector;
    HashMap<SelectionKey, ClientSession> clientMap = new HashMap<>();

    MyServer(InetSocketAddress socketAddress) throws Throwable {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector = Selector.open(), SelectionKey.OP_ACCEPT);
        serverSocketChannel.bind(socketAddress);

        start();
    }

    private void start() throws Throwable {
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.keys();
            Iterator<SelectionKey> it = keys.iterator();

            while (it.hasNext()) {
                SelectionKey key = it.next();

                if (!key.isValid()) continue;

                if (key.isAcceptable()) {
                    SocketChannel channel = serverSocketChannel.accept();
                    if (channel == null) continue;
                    channel.configureBlocking(false);
                    SelectionKey readKey = channel.register(selector, SelectionKey.OP_READ);
                    clientMap.put(readKey, new ClientSession(key, channel));
                    System.out.println("New Client IP="+channel.getRemoteAddress()+ ", total clients="+clientMap.size());
                }

                if (key.isReadable()) {
                    clientMap.get(key).read();
                }
            }

            selector.selectedKeys().clear();
        }
    }

    public static void main(String[] args) throws Throwable {
        new MyServer(new InetSocketAddress("localhost", 9898));
    }
}
