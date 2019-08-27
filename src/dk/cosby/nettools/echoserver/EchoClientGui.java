package dk.cosby.nettools.echoserver;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EchoClientGui implements SendEcho.EchoListener {


    public TextArea ta_echo_client;
    public TextField tf_echo_client;

    private EchoClient echoClient;

    public boolean firstConnection = true;

    public void startServer(ActionEvent actionEvent) {

        EchoServer server = new EchoServer();
        Thread thread = new Thread(server);
        thread.start();


    }

    public void SendEcho(ActionEvent actionEvent) {

        if(firstConnection){

            echoClient = new EchoClient(this);
            Thread thread = new Thread(echoClient);
            thread.start();
            firstConnection = false;
        } else {
            System.out.println("Button sending echo request");
            if(!tf_echo_client.getText().isEmpty()) {
                SendEcho sendEcho = new SendEcho(echoClient.getIn(), echoClient.getOut(), tf_echo_client.getText(), this);
                Thread thread = new Thread(sendEcho);
                thread.start();
            }
        }



    }

    @Override
    public void echo(String echo) {
        ta_echo_client.appendText(echo + "\n");
    }
}
