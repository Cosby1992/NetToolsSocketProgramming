package dk.cosby.nettools.Timeserver;

import dk.cosby.nettools.AppSettings;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;

public class TimeClient implements Runnable {

    public interface TimeListener {
        void sendClientMessage(String Message);
    }

    private DataOutputStream dos;
    private ObjectInputStream ois;
    private TimeListener listener;


    public TimeClient(TimeListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {

        try {
            listener.sendClientMessage("Connecting to server\n");
            Socket socket = new Socket(AppSettings.HOST, AppSettings.PORT);

            ois = new ObjectInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());


            dos.writeBoolean(true);
            dos.flush();

            Date date = (Date) ois.readObject();
            listener.sendClientMessage("Server answered " + date + "\n");
            System.out.println("\nServer answered " + date);

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
