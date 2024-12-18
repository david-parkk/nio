package org.example.nio.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class CharBufferViewTest2 {

    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(100);
        CharBuffer charBuffer=buffer.asCharBuffer();
        charBuffer.put("Hello World!");
        charBuffer.flip();
        String str=charBuffer.toString();
        System.out.println("data: "+str);
        System.out.println("Buffer position: "+charBuffer.position());

        int start=6;
        int end=12;
        CharSequence sub=charBuffer.subSequence(start,end);
        System.out.println(sub.toString());
    }
}
