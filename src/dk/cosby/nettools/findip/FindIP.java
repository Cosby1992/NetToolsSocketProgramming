package dk.cosby.nettools.findip;

import javafx.scene.control.TextArea;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class FindIP implements Runnable {

    private Socket socket;
    private TextArea textArea;
    private DataOutputStream dos;
    private ObjectInputStream ois;


    public FindIP(Socket socket, TextArea textArea) throws IOException {
        this.socket = socket;
        this.textArea = textArea;

        dos = new DataOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());

    }

    @Override
    public void run() {

        try {
        dos.writeBoolean(true);
        dos.flush();

        HostInfo hostInfo = (HostInfo) ois.readObject();
        textArea.appendText("\nHost address:port " + hostInfo.getHostName() + ":" + hostInfo.getHostPort());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }



}
