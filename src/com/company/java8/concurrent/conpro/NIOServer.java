package com.company.java8.concurrent.conpro;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by naresh.kapse on 26/08/16.
 */
public class NIOServer {


    public static void main(String[] args) throws IOException {
        new NIOServer().start(new InetSocketAddress("localhost", 9090));
    }

    private void start(InetSocketAddress address) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(address);
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();

                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        accept(key);
                    }else if (key.isReadable()) {
                        read(key);
                    }else if (key.isWritable()) {
                        write(key);
                    }
                }
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel)key.channel();

        SocketChannel socketChannel = ssc.accept();
        socketChannel.configureBlocking(false);
        System.out.println("Connected from: " + socketChannel.getRemoteAddress());
        socketChannel.register(key.selector(), SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel)key.channel();
        ByteBuffer buf = ByteBuffer.allocate(128);
        StringBuffer sbuff = new StringBuffer();

        int bytesRead = socketChannel.read(buf);
        if (bytesRead == -1) {
            System.out.println("Connection closed to " + socketChannel.getRemoteAddress());
            socketChannel.close();
            key.cancel();
            return;
        }
        buf.flip();

        for (int i = 0; i < buf.limit(); i++) {
            sbuff.append((char) buf.get());
        }

        buf.clear();
        doSomething(sbuff.toString(), key);
    }

    private void doSomething(String s, SelectionKey key) throws ClosedChannelException {
        s = s.toUpperCase();
        key.channel().register(key.selector(), SelectionKey.OP_WRITE, s);
    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel)key.channel();
        String rawResponse = (String)key.attachment();

        ByteBuffer buf = ByteBuffer.wrap(rawResponse.getBytes(StandardCharsets.UTF_8));

        socketChannel.write(buf);

        buf.clear();
        socketChannel.register(key.selector(), SelectionKey.OP_READ);
//        socketChannel.close();
    }
}
