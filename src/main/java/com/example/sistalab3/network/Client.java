package com.example.sistalab3.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;

        try (Socket socket = new Socket(host, port)) {
            String request = "Hello World!";

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println(request);
            writer.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            in.lines().forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
