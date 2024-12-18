package org.example.nio.selector;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class SelectorTest {

    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();
            ServerSocketChannel server = ServerSocketChannel.open();
            server.configureBlocking(false);

            ServerSocket socket=server.socket();
            SocketAddress addr= new InetSocketAddress(12345);
            socket.bind(addr);

            server.register(selector, SelectionKey.OP_ACCEPT);


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
