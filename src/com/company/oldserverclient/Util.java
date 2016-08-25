package com.company.oldserverclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by naresh.kapse on 16/08/16.
 */
public class Util {
    public static int transmogrify(int data) {
        return (Character.isLetter(data)) ? data ^ ' ' : data;
    }

    public static void process(Socket socket) throws IOException {
        System.out.println("Connection from " + socket);
        try {
            InputStream i = socket.getInputStream();
            OutputStream o = socket.getOutputStream();

            int data;
            while ((data = i.read()) != -1) {
                data = Util.transmogrify(data);
                o.write(data);
            }

        }catch (Exception e) {
            System.out.println("Connection problem - " + e);
            socket.close();
        }
    }

    public static void process(SocketChannel sc) throws IOException {
        System.out.println("Connection from " + sc);
        try {
            ByteBuffer buf = ByteBuffer.allocate(128);
            int bytesRead = sc.read(buf);
            while (bytesRead != -1) {
                buf.flip();

                for (int i = 0; i < buf.limit(); i++) {
                    buf.put(i, (byte) Util.transmogrify(buf.get(i)));
                }
                sc.write(buf);

                buf.compact();
            }
        }catch (Exception e) {
            System.out.println("Connection problem - " + e);
            sc.close();
        }
    }
}
