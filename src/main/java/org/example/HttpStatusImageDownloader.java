package org.example;

import java.io.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.example.HttpStatusChecker.HTTP_CAT_URL;

public class HttpStatusImageDownloader {
    public static void downloadStatusImage(int code) throws IOException {
        String imageUrl = HTTP_CAT_URL + code + ".jpg";

        URL url = new URL(imageUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {

            try (InputStream inputStream = connection.getInputStream()) {

                String fileName = code + ".jpg";
                File file = new File(fileName);

                try (FileOutputStream outputStream = new FileOutputStream(file)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }

                System.out.println("Image for status code " + code + " downloaded to " + fileName);
            }
        } else {
            throw new RuntimeException("Image not found for status code " + code);
        }
    }
}
