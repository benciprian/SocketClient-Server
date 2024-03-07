package ro.ubb.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TCPServer {

    private static final int PORT = 1234;
    private static ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static TCPServer instance;

    private TCPServer() {
    }

    public static synchronized TCPServer getInstance() {
        if (instance == null) {
            instance = new TCPServer();
        }
        return instance;
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("hello server!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("client connected.");

                ClientService calculatorService = new ClientService(socket);
                pool.execute(calculatorService);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientService implements Runnable {
    Socket client;

    public ClientService(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try (var inputStream = client.getInputStream();
             var outputStream = client.getOutputStream();
             var bf = new BufferedReader(new InputStreamReader(inputStream))) {

            String inputLine = bf.readLine();
            String[] parts = inputLine.split(",");
            String operationType = parts[0];

            switch (operationType) {
                case "1":
                    handleCase1(parts, outputStream);
                    break;
                case "2":
                    handleCase2(parts, outputStream);
                    break;
                case "3":
                    handleCase3(parts, outputStream);
                    break;
                case "4":
                    handleCase4(parts, outputStream);
                    break;
                default:
                    outputStream.write("Operație necunoscuta\n".getBytes(StandardCharsets.UTF_8));
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void handleCase1(String[] parts, OutputStream outputStream) {
        int sum = 0;
        for (int i = 1; i < parts.length; i++) {
            int currentNumber = Integer.parseInt(parts[i]);
            sum += currentNumber;
        }
        try {
            outputStream.write(String.valueOf(sum).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCase2(String[] parts, OutputStream outputStream) {
        int prod = 1;
        for (int i = 1; i < parts.length; i++) {
            int currentNumber = Integer.parseInt(parts[i]);
            prod *= currentNumber;
        }
        try {
            outputStream.write(String.valueOf(prod).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void handleCase3(String[] parts, OutputStream outputStream) throws IOException {
        int a = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        int d = Integer.parseInt(parts[3]);

        String result = findDivisibleNumbers(a, b, d) + "\n";
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
    }

    public static String findDivisibleNumbers(int a, int b, int d) {
        StringBuilder result = new StringBuilder();
        for (int i = a; i < b; i++) {
            if (i % d == 0) {
                result.append(i).append(" ");
            }
        }
        return result.toString().trim();
    }

    private void handleCase4(String[] parts, OutputStream outputStream) throws IOException {
        int a = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        int d = Integer.parseInt(parts[3]);

        String result = findDivisibleNumbers2(a, b, d) + "\n";
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
    }

    public static String findDivisibleNumbers2(int a, int b, int d) {
        StringBuilder result = new StringBuilder();
        for (int i = a; i < b; i++) {
            if ((i % 100) % d == 0) {
                result.append(i).append(" ");
            }
        }
        return result.toString().trim();
    }

}



