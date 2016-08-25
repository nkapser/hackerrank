package com.company.java8;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Created by naresh.kapse on 16/08/16.
 */
public class ClientSession {
    SelectionKey selectionKey;
    SocketChannel socketChannel;
    ByteBuffer buf;

    ClientSession(SelectionKey selectionKey, SocketChannel socketChannel) throws Throwable {
        this.selectionKey = selectionKey;
        this.socketChannel = socketChannel;
        this.socketChannel.configureBlocking(false);
        buf = ByteBuffer.allocate(128); //128 bytes capacity
    }

    void disconnect() {
        try {
            if (selectionKey != null) selectionKey.cancel();
            if (socketChannel == null) return;
            System.out.println("Bye Bye " + (InetSocketAddress)socketChannel.getRemoteAddress());
            socketChannel.close();
        }catch (Throwable t){}
    }

    void read() {
        try {
        // TODO: read from channel
            int bytesRead = socketChannel.read(buf);
            while (bytesRead != -1) {
                buf.flip();

                while (buf.hasRemaining()) {
//                    System.out.print((char)buf.get());
                    socketChannel.write(buf);
                }

                buf.compact();
            }
        }catch (Throwable t){
            disconnect();
            t.printStackTrace();
        }
    }
}
