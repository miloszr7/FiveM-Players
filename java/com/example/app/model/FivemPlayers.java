package com.example.app.model;

public class FivemPlayers {

    private int id;
    private String name;
    private int ping;

    public FivemPlayers() {}

    public FivemPlayers(int id, String name, int ping) {
        this.id = id;
        this.name = name;
        this.ping = ping;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

    @Override
    public String toString() {
        return "FivemPlayers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ping=" + ping +
                '}';
    }
}
