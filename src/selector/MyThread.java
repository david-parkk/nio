package org.example.nio.selector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MyThread extends Thread{

    private SocketChannel sc;
    public MyThread(SocketChannel sc) {
        this.sc=sc;
    }

    @Override
    public void run(){
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        try{
            while(!Thread.currentThread().isInterrupted()){
                buffer.clear();
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                String message = in.readLine();
                if(message.equals("quit")||message.equals("shutdown")){
                    System.exit(0);
                }
                buffer.put(message.getBytes());
                buffer.flip();
                sc.write(buffer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            clearBuffer(buffer);
        }
    }
    private void clearBuffer(ByteBuffer buffer) {
        if (buffer != null) {
            buffer.clear();  // Reset the position to 0, limit to capacity
        }
    }
}
