package org.example.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ViewBufferTest {

    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(10);
        IntBuffer ib=buffer.asIntBuffer();
        System.out.println("position: " + ib.position() + ", limit: " + ib.limit() +
                ", capacity: " + ib.capacity());

        ib.put(1024).put(2048);
        System.out.println("index_0="+ib.get(0)+", index_1="+ib.get(1));
        while(buffer.hasRemaining()){
            System.out.println(buffer.get()+" ");
        }
        //int는 4바이트
    }
}
