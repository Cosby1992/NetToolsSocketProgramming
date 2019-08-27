package dk.cosby.nettools.timeserver;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class TimeServerGui implements TimeClient.TimeListener, TimeServer.TimeListenerServer {
    public TextArea ta_timeserver;
    public TextArea ta_timeclient;

    public void startServer(ActionEvent actionEvent) {

        Thread thread = new Thread(new TimeServer(this));
        thread.start();


    }

    public void startClientSendRequest(ActionEvent actionEvent) {

        Thread thread = new Thread(new TimeClient(this));
        thread.start();


    }

    @Override
    public void sendClientMessage(String message) {
        ta_timeclient.appendText(message + "\n");
    }

    @Override
    public void sendServerMessage(String message) {
        ta_timeserver.appendText(message + "\n");
    }
}
