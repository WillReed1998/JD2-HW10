package org.example;

import java.util.Scanner;

public class HttpImageStatusCli {
    public static void main(String[] args) {
        HttpImageStatusCli httpCli = new HttpImageStatusCli();
        httpCli.askStatus();
    }

    public void askStatus(String imageUrl) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter HTTP status code: ");
        String input = scanner.nextLine();

        try {
            int statusCode = Integer.parseInt(input);

            if (statusCode >= 100 && statusCode < 600) {


                if (imageUrl != null) {
                    HttpStatusImageDownloader.downloadStatusImage(imageUrl);
                    System.out.println("Image downloaded successfully.");
                } else {
                    System.out.println("There is no image for HTTP status " + statusCode);
                }
            } else {
                System.out.println("Invalid HTTP status code. Please enter a valid number between 100 and 599.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } finally {
            scanner.close();
        }
    }
}
