package com.company.oldserverclient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by naresh.kapse on 16/08/16.
 */
public class MyExecutorService {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9898);
        ExecutorService executorService = new ThreadPoolExecutor(
                0, 1000,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.submit(() -> {
                try {
                    Util.process(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
