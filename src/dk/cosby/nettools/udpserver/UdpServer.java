package dk.cosby.nettools.udpserver;

import dk.cosby.nettools.AppSettings;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer implements Runnable {

    private String replyMessage = "Du har kontaktet en UDP-server som har returneret dette svar!";
    private String request;
    private int port;
    private InetAddress IPAddress;

    @Override
    public void run() {

        try {
            DatagramSocket serverSocket = new DatagramSocket(AppSettings.PORT);
            System.out.println("UDP server startet.");

            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                request = new String(receivePacket.getData());
                System.out.println("RECEIVED: " + request);

                IPAddress = receivePacket.getAddress();
                System.out.println("Client IP = " + IPAddress);

                port = receivePacket.getPort();
                System.out.println("Client port = " + port);

                sendData = replyMessage.getBytes();
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
                System.out.println("Data send to client");

            }
        } catch (IOException e){
            e.printStackTrace();
        }


    }


}
