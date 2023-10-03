package org.example;
import java.util.Scanner;
import java.io.IOException;

public class HttpImageStatusCli {

    public void askStatus(Scanner scanner) {
        do {
            System.out.print("Enter HTTP status code: ");
            String input = scanner.nextLine();

            try {
                int statusCode = Integer.parseInt(input);

                if (statusCode >= 100 && statusCode < 600) {
                    HttpStatusChecker checker = new HttpStatusChecker();
                    String imageUrl = null;
                    try {
                        imageUrl = checker.getStatusImage(statusCode);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    if (imageUrl != null) {
                        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
                        try {
                            downloader.downloadStatusImage(statusCode);
                            System.out.println("Image downloaded successfully.");
                            break;
                        } catch (IOException e) {
                            System.err.println("Error downloading image: " + e.getMessage());
                        }
                    } else {
                        System.out.println("There is no image for HTTP status " + statusCode);
                    }
                } else {
                    System.out.println("Invalid HTTP status code. Please enter a valid number between 100 and 599.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        } while (true);
    }
}


