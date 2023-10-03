package org.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpStatusChecker {

    static final String HTTP_CAT_URL = "https://http.cat/";

    public String getStatusImage(int code) throws IOException {
        String imageUrl = HTTP_CAT_URL + code + ".jpg";

        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                return null;
            }

            return imageUrl;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
