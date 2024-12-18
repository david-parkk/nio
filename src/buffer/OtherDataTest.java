package org.example.nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class OtherDataTest {
    public static void main(String[] args) {
        ByteBuffer buffer= ByteBuffer.allocate(10);
        print(buffer);
        buffer.putInt(100);
        print(buffer);

        buffer.position(0);
        while(buffer.hasRemaining()){
            System.out.println(buffer.get()+" ");
        }
    }
    public static void print(Buffer buffer){
        System.out.println("position: " + buffer.position() + ", limit: " + buffer.limit() +
                ", capacity: " + buffer.capacity());
    }
}
