package com.company.java8;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by naresh.kapse on 12/08/16.
 */
public class BigFileReader {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("/Users/naresh.kapse/Downloads/outfit7-deviceid-issue-report1-45ac7233-5cbd-451e-9763-0c0c3b750a64/outfit7-deviceid-issue-report1-45ac7233-5cbd-451e-9763-0c0c3b750a64_part-0.csv", "r");
        FileChannel fileChannel = raf.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(50);

        int bytesRead = fileChannel.read(buf);

        while (bytesRead != -1) {

//            System.out.println(bytesRead);
            buf.flip();

            while (buf.hasRemaining()) {
                System.out.print((char)buf.get());
            }

            buf.compact();
            bytesRead = fileChannel.read(buf);
        }

        fileChannel.close();
        raf.close();
    }
}
