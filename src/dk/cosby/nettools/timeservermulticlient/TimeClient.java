package dk.cosby.nettools.timeservermulticlient;

import dk.cosby.nettools.AppSettings;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class TimeClient implements Runnable {

    public interface TimeListener {
        void sendClientMessage(String Message);
    }


    private TimeListener listener;
    private Socket socket;
    private ObjectInputStream ois;
    private DataOutputStream dos;

    private boolean firstCon = true;
    private TimeRequest timeRequest;




    public TimeClient(TimeListener listener) {

            this.listener = listener;

    }

    @Override
    public void run() {

        if(firstCon) {
            listener.sendClientMessage("Connecting to server\n");
            firstCon = false;
            try {
                socket = new Socket(AppSettings.HOST, AppSettings.PORT);
                ois = new ObjectInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                timeRequest = new TimeRequest(ois, dos, listener);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Thread thread = new Thread(timeRequest);
        thread.start();

    }

}
