package org.example.nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BulkWriteTest {

    public static void main(String[] args) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.position(5);
        buffer.mark();
        System.out.println("position: " + buffer.position() + ", limit: " + buffer.limit());
        byte[] b = new byte[15];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) i;
        }
        int size = buffer.remaining();
        if (b.length < size) {
            size = b.length;
        }

        buffer.put(b, 0, size);
        System.out.println("position: " + buffer.position() + ", limit: " + buffer.limit());
        buffer.reset();
        doSomething(buffer, size);
    }

    public static void doSomething(ByteBuffer buffer, int size) {
        for (int i = 0; i < size; i++) {
            System.out.println("buffer.position() = " + buffer.position());
            System.out.println("byte = " + buffer.get());
        }
    }
}
