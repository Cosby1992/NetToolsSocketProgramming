package dk.cosby.nettools.findip;

import dk.cosby.nettools.AppSettings;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable{

    private Socket socket;
    private TextArea textArea;

    public Client(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void run() {

        try {
            socket = new Socket(AppSettings.HOST, AppSettings.PORT);
            textArea.appendText("\nClient connected to server");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Socket getSocket() {
        return socket;
    }
}
