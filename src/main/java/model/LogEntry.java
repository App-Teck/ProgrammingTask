package model;

public class LogEntry {
    //We can use Lombok for generation of boilerplate code (constructor, get, set, toString...)
    private final String ipAddress;
    private final String url;

    public LogEntry(String ipAddress, String url) {
        this.ipAddress = ipAddress;
        this.url = url;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "LogEntry{" + "ipAddress=" + ipAddress + ", url=" + url + '}';
    }
}
