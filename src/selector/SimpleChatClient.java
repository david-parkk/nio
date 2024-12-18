package org.example.nio.selector;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class SimpleChatClient {

    private Selector selector;
    private SocketChannel sc;

    public void initServer(){
        try{
            selector=Selector.open();
            sc=SocketChannel.open(new InetSocketAddress(12345));
            sc.configureBlocking(false);
            sc.register(selector, SelectionKey.OP_READ);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startServer(){
        startWriter();
        startReader();
    }

    private void startWriter() {
        new MyThread(sc);
    }
}
