package dk.cosby.nettools.portscanner;

import java.io.IOException;
import java.net.Socket;


public class PortScanner implements Runnable {

    public interface PortListener{
        void sendOpenPortUpdate(String report);
    }

    private String host;
    private int portStart;
    private int interval;

    private PortListener listener;

    public PortScanner(String host, int startingPort, int numOfPorts, PortListener listener) {
        this.host = host;
        portStart = startingPort;
        interval = numOfPorts;
        this.listener = listener;

    }

    @Override
    public void run() {

        for(int port = portStart; port <= portStart+interval; port++){

            try {
                Socket socket = new Socket(host, port);
                String text = host + " is listening on port " + port + "\n";
                System.out.println(text);
                listener.sendOpenPortUpdate(text);

                socket.close();
            } catch (IOException e) {
                // empty catch block
            }

        }




//        textArea.setText("Scan started\n");
//
//        for (int port = 0; port <= 65535; port++) {
//
//            try {
//                Socket socket = new Socket(host, port);
//                String text = host + " is listening on port " + port;
//                textArea.appendText(text + "\n");
//                socket.close();
//            } catch (IOException e) {
//                // empty catch block
//            }
//        }


    }

}
