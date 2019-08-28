package dk.cosby.nettools.udpserver;

import dk.cosby.nettools.AppSettings;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client implements Runnable {

    private String sentence = "Requesting data";
    private String serverResponse;
    private Listener listener;

    public Client(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {

        try {
            DatagramSocket clientSocket = new DatagramSocket();
            listener.clientOutput("connected to server");

            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, AppSettings.PORT);
            clientSocket.send(sendPacket);
            listener.clientOutput("Sending request");

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            serverResponse = new String(receivePacket.getData());
            listener.clientOutput("Response from server: " + serverResponse);
            clientSocket.close();
            listener.clientOutput("Connection closed");

        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
