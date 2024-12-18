import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) {
        NioClient nioClient = new NioClient();

        nioClient.start(8080,new Scanner(System.in));
    }


}
