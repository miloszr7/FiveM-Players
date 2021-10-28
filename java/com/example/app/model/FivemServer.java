package com.example.app.model;

import java.util.List;

public class FivemServer {

    private String endpoint;
    private int online;
    private int maxOnline;
    private int queue;
    private String hostName;
    private List<FivemPlayers> fivemPlayers;

    public FivemServer() {}

    public FivemServer(String endpoint, int online, int maxOnline, int queue, String hostName, List<FivemPlayers> fivemPlayers) {
        this.endpoint = endpoint;
        this.online = online;
        this.maxOnline = maxOnline;
        this.queue = queue;
        this.hostName = hostName;
        this.fivemPlayers = fivemPlayers;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getMaxOnline() {
        return maxOnline;
    }

    public void setMaxOnline(int maxOnline) {
        this.maxOnline = maxOnline;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public List<FivemPlayers> getFivemPlayers() {
        return fivemPlayers;
    }

    public void setFivemPlayers(List<FivemPlayers> fivemPlayers) {
        this.fivemPlayers = fivemPlayers;
    }

    @Override
    public String toString() {
        return "FivemServer{" +
                "endpoint='" + endpoint + '\'' +
                ", online=" + online +
                ", maxOnline=" + maxOnline +
                ", queue=" + queue +
                ", hostName='" + hostName + '\'' +
                ", fivemPlayers=" + fivemPlayers +
                '}';
    }
}
