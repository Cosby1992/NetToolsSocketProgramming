package dk.cosby.nettools.portscanner;

public class ThreadHandler implements Runnable {

    private int threadInterval;
    private int startingPort;
    private int numOfThreads;
    private String ipAddress;
    private PortScanner.PortListener listener;


    public ThreadHandler(int numOfThreads, String ipAddress, PortScanner.PortListener listener) {

        this.numOfThreads = numOfThreads;
        this.ipAddress = ipAddress;
        this.listener = listener;

        threadInterval = 65535/numOfThreads;
        startingPort = 0;

    }

    @Override
    public void run() {

        System.out.println("Scanning...");

        for (int i = 0; i < numOfThreads; i++) {
            PortScanner portScanner = new PortScanner(ipAddress, startingPort, threadInterval, listener);
            Thread thread = new Thread(portScanner);
            thread.start();

            System.out.println("Thread " + i + " was launched");
            startingPort += threadInterval;
        }
    }



}
