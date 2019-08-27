package dk.cosby.nettools.Timeserver;

import dk.cosby.nettools.AppSettings;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServer implements Runnable {

    public interface TimeListenerServer {
        void sendServerMessage(String Message);
    }

    private Socket socket;
    private DataInputStream dis;
    private ObjectOutputStream oos;
    private TimeListenerServer listenerServer;

    public TimeServer(TimeListenerServer listenerServer){
        this.listenerServer = listenerServer;
    }

    @Override
    public void run() {

        try {
            System.out.println("Starting server");
            listenerServer.sendServerMessage("Starting server\n");

            ServerSocket serverSocket = new ServerSocket(AppSettings.PORT);
            System.out.println("Port allocated for serverhosting");
            listenerServer.sendServerMessage("Port allocated for serverhosting\n");

            System.out.println("Waiting for client to connect");
            listenerServer.sendServerMessage("Waiting for client to connect\n");
            socket = serverSocket.accept();

            System.out.println("Client connected");
            listenerServer.sendServerMessage("Client connected\n");

            dis = new DataInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());

            while (socket.isConnected()) {
                boolean boo = dis.readBoolean();

                if (boo) {
                    Date date = new Date();
                    oos.writeObject(date);
                    oos.flush();
                }
            }

        } catch (EOFException eof){
            System.out.println("Client closed connection");
            listenerServer.sendServerMessage("Client closed connection\n");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
