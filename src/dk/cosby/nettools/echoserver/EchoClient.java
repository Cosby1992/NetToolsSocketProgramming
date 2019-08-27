package dk.cosby.nettools.echoserver;

import dk.cosby.nettools.AppSettings;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class EchoClient implements Runnable {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private SendEcho.EchoListener listener;

    public EchoClient(SendEcho.EchoListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {

        try {

            socket = new Socket(AppSettings.HOST, AppSettings.PORT);
            listener.echo("Client connected to server");
            listener.echo("Timestamp: " + new Date());
            System.out.println("Client connected to server");


            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public DataInputStream getIn() {
        return in;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }
}
