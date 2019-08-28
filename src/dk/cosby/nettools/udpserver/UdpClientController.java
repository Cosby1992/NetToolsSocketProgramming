package dk.cosby.nettools.udpserver;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class UdpClientController implements Listener {

    public TextArea ta_client_info;
    private UdpServer server;
    private Client client;



    public void startServer(ActionEvent actionEvent) {

        server = new UdpServer();
        Thread thread = new Thread(server);
        thread.start();

    }

    public void sendRequest(ActionEvent actionEvent) {

        client = new Client(this);
        Thread thread = new Thread(client);
        thread.start();

    }

    @Override
    public void clientOutput(String out) {
        ta_client_info.appendText(out + "\n");
    }
}
