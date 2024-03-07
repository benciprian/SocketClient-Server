package ro.ubb.server;

public class ClientApp {
    public static void main(String[] args) {
        TCPClient client = new TCPClient();
        ConsoleUI consoleUI = new ConsoleUI(client);
        consoleUI.start();
    }
}