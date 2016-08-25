package com.company.oldserverclient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by naresh.kapse on 16/08/16.
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9898);
        while (true) {
            Socket socket = serverSocket.accept();
            Util.process(socket);
        }
    }


}
