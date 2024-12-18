package org.example.nio.buffer;

public class ByteBuffer2 {

    public static void main(String[] args) {
        java.nio.ByteBuffer buffer = java.nio.ByteBuffer.allocate(10);
        System.out.print("Init Position : " + buffer.position());
        System.out.print(", Init Limit : " + buffer.limit());
        System.out.println(", Init Capacity : " + buffer.capacity());

        buffer.put(3,(byte) 3);
        buffer.put(4,(byte) 4);
        buffer.put(5,(byte) 5);



        System.out.println("Value: " + buffer.get(3) + ", Position : " + buffer.position() + "\n");
        System.out.println("Value: " + buffer.get(4) + ", Position : " + buffer.position() + "\n");
        System.out.println("Value: " + buffer.get(5) + ", Position : " + buffer.position() + "\n");
        System.out.println("Value: " + buffer.get() + ", Position : " + buffer.position() + "\n");

    }
}
