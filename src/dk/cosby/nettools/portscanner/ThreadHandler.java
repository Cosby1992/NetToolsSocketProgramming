package dk.cosby.nettools.portscanner;

public class ThreadHandler implements Runnable {

    private int threadInterval;
    private int startingPort;
    private int numOfThreads;
    private String ipAddress;


    public ThreadHandler(int numOfThreads, String ipAddress) {

        this.numOfThreads = numOfThreads;
        this.ipAddress = ipAddress;

        threadInterval = 65535/numOfThreads;
        startingPort = 0;

    }

    @Override
    public void run() {

        System.out.println("Scanning...");

        for (int i = 0; i < numOfThreads; i++) {
            PortScanner portScanner = new PortScanner(ipAddress, startingPort, threadInterval);
            Thread thread = new Thread(portScanner);
            thread.start();

            startingPort += threadInterval;
        }
    }



}
