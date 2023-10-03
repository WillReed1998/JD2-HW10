package org.example;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        final int PORT = 80;
        final String HOST = "http.cat";
        Scanner scanner = new Scanner(System.in);

        try (
                Socket socket = new Socket(HOST, PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStream out = socket.getOutputStream()
        ) {
            System.out.println("Connected to the server: http.cat:" + PORT);

            HttpImageStatusCli httpCli = new HttpImageStatusCli();
            httpCli.askStatus(scanner);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
    }
}
