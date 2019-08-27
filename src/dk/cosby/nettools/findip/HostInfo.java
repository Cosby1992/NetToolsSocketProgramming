package dk.cosby.nettools.findip;

import java.io.Serializable;

public class HostInfo implements Serializable {

    private String hostName;
    private int hostPort;

    public HostInfo(String hostName, int hostPort) {
        this.hostName = hostName;
        this.hostPort = hostPort;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getHostPort() {
        return hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }
}
