package ro.ubb.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class TCPClient {
    public void sendAndReceive(int optiune,int a, int b, int d) {
        try (Socket connection = new Socket("localhost", 1234);
             var outputStream = connection.getOutputStream();
             BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

            String request = optiune + "," + a + "," + b + "," + d + "\n";
            outputStream.write(request.getBytes(StandardCharsets.UTF_8));

            String result = bf.readLine();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAndReceiveList(int optiune, String numbersList) {
        try (Socket connection = new Socket("localhost", 1234);
             var outputStream = connection.getOutputStream();
             BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

            String request = optiune + "," + numbersList + "\n";
            outputStream.write(request.getBytes(StandardCharsets.UTF_8));

            String result = bf.readLine();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

