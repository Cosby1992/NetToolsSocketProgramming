package dk.cosby.nettools.currentip;

import dk.cosby.nettools.AppSettings;
import dk.cosby.nettools.findip.HostInfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private HostInfo currentInfo;
    private DataInputStream dis;
    private ObjectOutputStream oos;

    @Override
    public void run() {

        try {
            ServerSocket serverSocket = new ServerSocket(AppSettings.PORT);

            Socket socket = serverSocket.accept();

            dis = new DataInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());


            boolean bool = dis.readBoolean();

            if(bool){
                currentInfo = new HostInfo(socket.getRemoteSocketAddress().toString(), socket.getPort());

                oos.writeObject(currentInfo);
                oos.flush();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
