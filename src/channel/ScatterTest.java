package org.example.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ScatteringByteChannel;

public class ScatterTest {

    public static void main(String[] args) throws IOException {


        FileInputStream fin =new FileInputStream("src/main/java/org/example/nio/channel/news.txt");

        ScatteringByteChannel channel=fin.getChannel();
        ByteBuffer header=ByteBuffer.allocateDirect(10);
        ByteBuffer body=ByteBuffer.allocateDirect(200);
        ByteBuffer[] buffers={header,body};
        int readCount=(int)channel.read(buffers);
        channel.close();
        System.out.println("read count : "+readCount);
        System.out.println("\n//====================================//\n");
        header.flip();
        body.flip();
        byte[]b=new byte[10];
        header.get(b);
        System.out.println("Header: "+new String(b));
        System.out.println("\n//====================================//\n");
        byte[]bb=new byte[200];
        body.get(bb);
        System.out.println("Doby: "+new String(bb));
    }
}
