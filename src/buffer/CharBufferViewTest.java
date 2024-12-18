package org.example.nio.buffer;

import java.nio.CharBuffer;

public class CharBufferViewTest {

    public static void main(String[] args) {
        CharBuffer buffer=CharBuffer.wrap("슬슬 방학각");
        System.out.println("position: " + buffer.position() + ", limit: " + buffer.limit() +
                ", capacity: " + buffer.capacity());
        while(buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
