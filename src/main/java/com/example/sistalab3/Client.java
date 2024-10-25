package com.example.sistalab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String host = "fungover.org";
        int port = 80;

        try (Socket socket = new Socket(host, port)) {
            String request = """
                    GET / HTTP/1.1
                    Host: fungover.org
                    Connection: close
                    
                    """;

            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.print(request);
            writer.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            in.lines().forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
