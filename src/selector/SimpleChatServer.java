package org.example.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SimpleChatServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private ServerSocket serverSocket;
    private Set<SocketChannel> room = new HashSet<>();

    public void initServer(){
        try{
            selector=Selector.open();
            serverSocketChannel= ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocket=serverSocketChannel.socket();

            InetSocketAddress isa = new InetSocketAddress(12345);
            serverSocket.bind(isa);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startServer(){
        try{
            while(true){
                selector.select();
                Iterator it =selector.selectedKeys().iterator();
                while(it.hasNext()){
                    SelectionKey key=(SelectionKey) it.next();
                    if(key.isAcceptable()){
                        accept(key);
                    } else if(key.isReadable()){
                        read(key);
                    }
                    it.remove();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void accept(SelectionKey key){
        ServerSocketChannel serverSocketChannel1=(ServerSocketChannel) key.channel();
        SocketChannel sc;
        try{
            sc=serverSocketChannel1.accept();
            registerChannel(selector,sc,SelectionKey.OP_READ);
            System.out.println(sc.toString()+" 클라이언트가 접속하였습니다.");
        }catch (Exception e){

            e.printStackTrace();
        }
    }
    private void registerChannel(Selector selector, SocketChannel sc,int ops){

        try{
            if(sc==null){
                System.out.println("Invalid Connection");
                return;
            }
            sc.configureBlocking(false);
            sc.register(selector,ops);
            addUser(sc);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void read(SelectionKey key){
        SocketChannel sc=(SocketChannel) key.channel();
        ByteBuffer buffer= ByteBuffer.allocateDirect(1024);
        try{
            int read = sc.read(buffer);
            System.out.println(read+ " byte를 읽었습니다");
        }catch(Exception e){
            try{
                sc.close();
            } catch (Exception e1){
                removeUser(sc);
                System.out.println(sc.toString()+" 클라이언트가 종료했습니다");
            }
        }

        try{
            broadcast(buffer);
        }catch (Exception e){

        }
    }

    private void broadcast(ByteBuffer buffer) {
        buffer.flip();
        Iterator iter=room.iterator();
        while(iter.hasNext()){
            SocketChannel sc=(SocketChannel) iter.next();
            try{
                if(sc!=null){
                    sc.write(buffer);
                    buffer.rewind();
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    private void addUser(SocketChannel sc) {
        room.add(sc);  // Add client to the room
    }

    private void removeUser(SocketChannel sc) {
        room.remove(sc);  // Remove client from the room
    }
}
