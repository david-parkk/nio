public class ServerMain {

    public static void main(String[] args) {
        NioServer nioServer = new NioServer();
        nioServer.start(8080);
    }
}
