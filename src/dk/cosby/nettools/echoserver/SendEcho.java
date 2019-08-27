package dk.cosby.nettools.echoserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SendEcho implements Runnable {

    public interface EchoListener {

        void echo(String echo);

    }

    private DataInputStream in;
    private DataOutputStream out;
    private String echo;
    private EchoListener listener;

    public SendEcho(DataInputStream in, DataOutputStream out, String echo, EchoListener listener) {
        this.in = in;
        this.out = out;
        this.echo = echo;
        this.listener = listener;
    }

    @Override
    public void run() {

        System.out.println("Sending Echo");

        try {
            listener.echo("Client: " + echo);
            out.writeUTF(echo);

            echo = in.readUTF();
            listener.echo("Server: " + echo);
            System.out.println(echo);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
