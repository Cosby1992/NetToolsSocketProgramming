package dk.cosby.nettools.timeservermulticlient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;

public class TimeRequest implements Runnable {

    private DataOutputStream dos;
    private ObjectInputStream ois;
    TimeClient.TimeListener listener;

    public TimeRequest(ObjectInputStream ois, DataOutputStream dos, TimeClient.TimeListener listener) {
        this.dos = dos;
        this.ois = ois;
        this.listener = listener;

    }

    @Override
    public void run() {

        try {

            dos.writeBoolean(true);
            dos.flush();

            Date date = (Date) ois.readObject();
            System.out.println("Server answered " + date);
            listener.sendClientMessage("Server answered: " + date);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
