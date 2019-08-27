package dk.cosby.nettools.findip;

import dk.cosby.nettools.AppSettings;
import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private Socket socket;
    private TextArea textArea;
    private DataInputStream dis;
    private ObjectOutputStream oos;

    public Server(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void run() {

        try {
            System.out.println("Starting server");

            ServerSocket serverSocket = new ServerSocket(AppSettings.PORT);
            System.out.println("Port allocated for serverhosting");

            System.out.println("Waiting for client to connect");
            socket = serverSocket.accept();

            System.out.println("Client connected");

            textArea.appendText("\nclient adress:port " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());

            dis = new DataInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());

            while (socket.isConnected()) {
                boolean boo = dis.readBoolean();

                if (boo) {
                    HostInfo hostInfo = new HostInfo(socket.getInetAddress().getHostName(), socket.getPort());
                    oos.writeObject(hostInfo);
                    oos.flush();

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
