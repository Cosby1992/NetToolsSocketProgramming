package dk.cosby.nettools.echoserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EchoHandler implements Runnable {

    private Socket clientSocket;

    public EchoHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // open up IO streams
            DataInputStream in  = new DataInputStream (clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            // waits for data and reads it in until connection dies
            String s;

            System.out.println("ClientHandler online");

            while (clientSocket.isConnected()) {
                s = in.readUTF();
                out.writeUTF(s);
                System.out.println("client echo send");
            }

            // close IO streams, then socket
            System.out.println("Closing connection with client");
            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e){
            System.out.println("Error with client handling");
        }

    }
}
