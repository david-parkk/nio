package org.example.nio.buffer;

import java.nio.CharBuffer;

public class CharBufferTest {

    public static void main(String[] args) {
        CharBuffer c = CharBuffer.allocate(10);
        System.out.println("position : "+c.position());
        c.put("hello NIO");
        System.out.println("position : "+c.position());
    }
}
