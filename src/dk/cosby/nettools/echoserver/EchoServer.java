package dk.cosby.nettools.echoserver;

import dk.cosby.nettools.AppSettings;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer implements Runnable {


    @Override
    public void run() {

        try {
            // create socket
            ServerSocket serverSocket = new ServerSocket(AppSettings.PORT);

            System.out.println("Started server on port " + AppSettings.PORT);

            // repeatedly wait for connections, and process
            while (true) {

                // a "blocking" call which waits until a connection is requested
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from client");

                Thread thread = new Thread(new EchoHandler(clientSocket));
                thread.start();

                System.out.println("Echohandler started");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
