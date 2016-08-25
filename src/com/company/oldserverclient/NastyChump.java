package com.company.oldserverclient;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by naresh.kapse on 16/08/16.
 */
public class NastyChump {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3000; i++) {
            try {
                Socket s = new Socket("localhost", 9898);
                System.out.println(i+1);
            } catch (IOException e) {
                System.out.println("Could not connect " + e);
                e.printStackTrace();
            }
        }

        Thread.sleep(100000000);
    }
}
