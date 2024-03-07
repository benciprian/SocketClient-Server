package ro.ubb.server;

public class ServerApp {
    public static void main(String[] args) {
        TCPServer.getInstance().startServer();
    }
}