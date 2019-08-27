package dk.cosby.nettools.portscanner;

import java.io.IOException;
import java.net.Socket;

public class PortScanner implements Runnable {

    private String host;
    private int portStart;
    private int interval;

    public PortScanner(String host, int startingPort, int numOfPorts) {
        this.host = host;
        portStart = startingPort;
        interval = numOfPorts;

    }

    @Override
    public void run() {

        for(int port = portStart; port <= portStart+interval; port++){

            try {
                Socket socket = new Socket(host, port);
                String text = host + " is listening on port " + port;
                System.out.println(text);
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
