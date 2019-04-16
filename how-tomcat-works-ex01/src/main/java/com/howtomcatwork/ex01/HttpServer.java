package com.howtomcatwork.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    private  boolean shutdown = false;


    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.await();
    }
    public void await(){
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while(!shutdown){
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                // create Request
                Request request = new Request(inputStream);
                request.parse();

                // create Response
                Response response = new Response(outputStream);
                response.setRequest(request);
                response.sendStaticResource();


                socket.close();

                //shutdown = request.getUri().equals(SHUTDOWN_COMMAND);

            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

        }
    }
}
