package org.example;
import java.io.*;
import java.net.*;

public class App {

    public static void main(String[] args) {
        final int PORT = 80;
        final String HOST = "http.cat";


        try (
                Socket socket = new Socket(HOST, PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStream out = socket.getOutputStream()
        ) {
            System.out.println("Connected to the server: http.cat:" + PORT);

            HttpStatusChecker checker = new HttpStatusChecker();
            try {
                String imageUrl = checker.getStatusImage(statusCode);
                System.out.println("Image URL for status code " + statusCode + ": " + imageUrl);
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }

            HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
            try {
                downloader.downloadStatusImage(imageCode);
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
