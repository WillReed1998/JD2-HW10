package org.example;
import java.io.IOException;
import java.util.Scanner;

public class HttpImageStatusCli {

    public void askStatus(Scanner scanner) {
        System.out.print("Enter HTTP status code: ");
        String input = scanner.nextLine();

        try {
            int statusCode = Integer.parseInt(input);

            if (statusCode >= 100 && statusCode < 600) {
                HttpStatusChecker checker = new HttpStatusChecker();
                String imageUrl = checker.getStatusImage(statusCode);

                if (imageUrl != null) {
                    HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
                    downloader.downloadStatusImage(statusCode);
                    System.out.println("Image downloaded successfully.");
                } else {
                    System.out.println("There is no image for HTTP status " + statusCode);
                }
            } else {
                System.out.println("Invalid HTTP status code. Please enter a valid number between 100 and 599.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

