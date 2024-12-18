package org.example.nio.buffer;

import java.nio.ByteBuffer;

public class SliceTest {

    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(10);
        buffer.put((byte) 0).put((byte) 1).put((byte) 2).put((byte) 3).put((byte) 4)
                .put((byte) 5).put((byte) 6).put((byte) 7).put((byte) 8).put((byte) 9);
        buffer.position(3);
        buffer.limit(9);
        ByteBuffer copyBuffer=buffer.slice();
        System.out.println("position: " + copyBuffer.position() + ", limit: " + copyBuffer.limit() +
                ", capacity: " + copyBuffer.capacity());
        while(copyBuffer.hasRemaining()){
            System.out.print(copyBuffer.get()+" ");
        }
        System.out.println();
        buffer.put(3,(byte)10);
        System.out.println(buffer.get(3));
        System.out.println(copyBuffer.get(0));
        buffer.put(4,(byte)11);
        System.out.println(buffer.get(4));
        System.out.println(copyBuffer.get(1));

    }
}
