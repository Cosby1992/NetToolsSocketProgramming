package dk.cosby.nettools.findip;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class FindIpGui {

    public TextArea ta_server_info;
    public Button btn_start_server;
    public TextArea ta_client_info;

    private Server server;
    private Client client;
    private FindIP findIP;

    public void initialize(){

        server = new Server(ta_server_info);
        client = new Client(ta_client_info);

    }

    public void start_server(ActionEvent actionEvent) {

        ta_server_info.appendText("Starting server");
        Thread thread = new Thread(server);
        thread.start();

    }

    public void startClient(ActionEvent actionEvent) {

        ta_client_info.appendText("Starting client");
        Thread thread = new Thread(client);
        thread.start();

    }

    public void getHostAddress(ActionEvent actionEvent) {

        try {
            findIP = new FindIP(client.getSocket(), ta_client_info);

            Thread thread = new Thread(findIP);
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
