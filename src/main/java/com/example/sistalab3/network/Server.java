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
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Client connected...");
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String message = in.readLine();
                    writer.println(message);
                    writer.flush();

                } catch (IOException e) {
                    System.out.println("Exception in client connection");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
