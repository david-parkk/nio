package org.example.nio.channel;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class SimpleChannelTest {

    public static void main(String[] args) throws Exception{
        ReadableByteChannel src= Channels.newChannel(System.in);
        WritableByteChannel dest= Channels.newChannel(System.out);
        ByteBuffer buffer= ByteBuffer.allocateDirect(1024);
        while(src.read(buffer)!=1){
            buffer.flip();
            while(buffer.hasRemaining()){
                dest.write(buffer);
            }
            buffer.clear();
        }
    }
}
