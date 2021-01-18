package com.knighenko.sweetvinegar.model;

import com.knighenko.sweetvinegar.entity.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectServer {
    private final Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    public ConnectServer(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }
    /**Метод для соединения с сервером, возвращает строку
     * @param request - адрес URL*/

    public String readResponse(String request) throws IOException {
        outputStream = new DataOutputStream(socket.getOutputStream());
        System.out.println("Client connected to server");
        while (!socket.isOutputShutdown()) {
            outputStream.writeUTF(request);
            outputStream.flush();

            inputStream = new DataInputStream(socket.getInputStream());

            String message = inputStream.readUTF();
            System.out.println("Response from server is: " + message);
            inputStream.close();
            outputStream.close();
            socket.close();
            System.out.println("Client disconected!");
            return message;
        }
        return null;
    }

    /**
     * Method connecting to server with parameter Request
     */

    public static String connectToServerSearch(String request) {

        final String[] response = {"false"};
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    ConnectServer connectServer = new ConnectServer(Constants.SERVER_IP, Constants.PORT);
                    response[0] = connectServer.readResponse(request);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response[0];
    }
}
