package org.example.nio.buffer;

public class ByteBuffer1 {


    public static void main(String[] args) {
        java.nio.ByteBuffer buffer = java.nio.ByteBuffer.allocate(10);
        System.out.print("Init Position : " + buffer.position());
        System.out.print(", Init Limit : " + buffer.limit());
        System.out.println(", Init Capacity : " + buffer.capacity());
        buffer.mark();

        buffer.put((byte) 10).put((byte) 11).put((byte) 12);
        buffer.reset();

        System.out.println("Value: " + buffer.get() + ", Position : " + buffer.position() + "\n");
        System.out.println("Value: " + buffer.get() + ", Position : " + buffer.position() + "\n");
        System.out.println("Value: " + buffer.get() + ", Position : " + buffer.position() + "\n");
        System.out.println("Value: " + buffer.get() + ", Position : " + buffer.position() + "\n");

        /*buffer.reset();
        byte[] bytes= new byte[100];
        buffer.get(bytes);
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
        System.out.println("end");*/
    }
}
