package dk.cosby.nettools.currentip;

import dk.cosby.nettools.AppSettings;
import dk.cosby.nettools.findip.HostInfo;
import javafx.scene.control.TextArea;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Client implements Runnable {

    private DataOutputStream dos;
    private ObjectInputStream ois;
    private TextArea textArea;

    public Client(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void run() {

        try {
            Socket socket = new Socket(AppSettings.HOST, AppSettings.PORT);

            dos = new DataOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            dos.writeBoolean(true);
            dos.flush();

            HostInfo currentIP = (HostInfo) ois.readObject();
            textArea.appendText("\ncurrent IP and port = " + currentIP.getHostName());

            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }






}
