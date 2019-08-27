package dk.cosby.nettools.timeservermulticlient;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class TimeServerGui implements TimeClient.TimeListener, TimeServer.TimeListenerServer {
    public TextArea ta_timeserver;
    public TextArea ta_timeclient;

    private TimeClient timeClient;
    private boolean firstCon = true;

    public void startServer(ActionEvent actionEvent) {

        Thread thread = new Thread(new TimeServer(this));
        thread.start();


    }

    public void startClientSendRequest(ActionEvent actionEvent) {

        if(firstCon) {
            timeClient = new TimeClient(this);
            firstCon = false;
        }

        Thread thread = new Thread(timeClient);
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
