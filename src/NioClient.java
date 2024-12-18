import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Scanner;

public class NioClient {

    public void start(final int portNumber,final Scanner scanner){
        try(SocketChannel socketChannel = SocketChannel.open()){
            socketChannel.connect(new InetSocketAddress(portNumber));
            System.out.println("Connection established!");
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while(true){
                var line =scanner.nextLine();
                if(line.equalsIgnoreCase("quit")){
                    break;
                }
                line+=System.lineSeparator();
                buffer.clear().put(line.getBytes()).flip();
                while(buffer.hasRemaining()){
                    socketChannel.write(buffer);
                }
                buffer.clear();
                int read = socketChannel.read(buffer);
                if(read>0){
                    buffer.flip();
                    byte[] bytes = new byte[read];
                    buffer.get(bytes);
                    System.out.println("received data : "+ Arrays.toString(bytes)+" , "+buffer.getInt(0));
                    //String data = new String(buffer.array(), buffer.position(), read);
                    //System.out.println("received data : "+data);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
