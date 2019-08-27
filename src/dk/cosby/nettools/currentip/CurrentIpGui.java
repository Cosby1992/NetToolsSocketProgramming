package dk.cosby.nettools.currentip;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class CurrentIpGui {


    public TextArea ta_server;
    public TextArea ta_client;

    public void startServer(ActionEvent actionEvent) {

        Thread thread = new Thread(new Server());
        thread.start();

    }

    public void startClientAndDoMagic(ActionEvent actionEvent) {

        Thread thread = new Thread(new Client(ta_client));
        thread.start();

    }
}
