package com.example.sistalab3.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                    Socket socket = serverSocket.accept();
                    Thread.ofVirtual().start(() -> handleClient(socket));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handleClient(Socket socket) {
        try (Socket clientSocket = socket) {
            System.out.println("[" + Thread.currentThread().threadId() + "] " + "Client connected...");
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message = in.readLine();
            writer.println("Echo from server: " + message);
            writer.flush();

        } catch (Exception e) {
            System.out.println("Exception in client connection.");
        }
    }
}
