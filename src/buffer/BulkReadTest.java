package org.example.nio.buffer;

public class BulkReadTest {

    public static void main(String[] args) {
        java.nio.ByteBuffer buffer = java.nio.ByteBuffer.allocate(10);
        buffer.put((byte)0).put((byte)1).put((byte)2).put((byte)3).put((byte)4);
        buffer.mark();
        buffer.put((byte)5).put((byte)6).put((byte)7).put((byte)8).put((byte)9);
        buffer.reset();
        byte[]b=new byte[15];
        int size=buffer.remaining();
        System.out.println("size = " + size);
        System.out.println("b.length = " + b.length);
        if(b.length<size){
            size=b.length;
        }
        buffer.get(b,0,size);
        System.out.println("position: "+buffer.position()+", limit: "+buffer.limit());
        doSomething(b,size);
        System.out.print("Init Position : " + buffer.position());
        System.out.print(", Init Limit : " + buffer.limit());
        System.out.println(", Init Capacity : " + buffer.capacity());

    }
    public static void doSomething(byte[] b, int size){
        for(int i=0;i<size;i++){
            System.out.println("byte = "+b[i]);
        }
    }
}
