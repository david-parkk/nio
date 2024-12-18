import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Set;

public class NioServer {
    public void start(final int portNumber){
        Set<SocketChannel> clients=new HashSet<>();
        try(ServerSocketChannel serverSocketChannel= ServerSocketChannel.open();
            Selector selector=Selector.open();){
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(portNumber));
            SelectionKey register = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("register.interestOps()= "+register.interestOps());
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while(true){
                if(selector.select()==0){
                    continue;
                }
                for(SelectionKey key : selector.selectedKeys()){
                    if(key.isAcceptable()){
                        if(key.channel() instanceof ServerSocketChannel channel){
                            //block 이 아니다?
                            SocketChannel client = channel.accept();
                            Socket socket = client.socket();
                            System.out.println(socket.getInetAddress().getHostAddress()+" : "+socket.getPort()+" connect ");
                            client.configureBlocking(false);
                            client.register(selector,SelectionKey.OP_READ);
                            clients.add(client);
                        }
                    }
                    else if(key.isReadable()){
                        if(key.channel() instanceof SocketChannel client) {
                            int read = client.read(buffer);
                            if(read==-1){
                                Socket socket = client.socket();
                                System.out.println(socket.getInetAddress().getHostAddress()+" : "+socket.getPort()+" connect ");
                                System.out.println("Disconnected");
                                client.close();
                                clients.remove(client);
                            }
                            buffer.flip();
                            String data = new String(buffer.array(), buffer.position(), read);
                            System.out.println("data : "+data);
                            buffer.putInt(read).flip();
                            /*while(buffer.hasRemaining()){
                                client.write(buffer);
                            }*/

                            for (SocketChannel otherClient : clients) {
                                if (otherClient != client) { // 자기 자신에게는 보내지 않음
                                    buffer.rewind();
                                    buffer.clear();
                                    buffer.put(data.getBytes());
                                    buffer.flip();
                                    while (buffer.hasRemaining()) {
                                        otherClient.write(buffer);
                                    }
                                }
                            }
                            buffer.clear();

                        }
                        else {
                            throw new RuntimeException();
                        }
                    }
                }
                selector.selectedKeys().clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            for (SocketChannel client : clients) {
                try{
                    client.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    private void test(Selector selector,int portNumber) throws IOException {

        SocketChannel serverSocketChannel= SocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(portNumber));
        SelectionKey register = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("register.interestOps()= "+register.interestOps());

    }
}
