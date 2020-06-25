package com.gmailat.pm.entity;

public class Client {

    private int id;
    private String name;
    private float cash;

    public Client() {
        name = null;
        cash = 0.0f;
        id = 0;
    }

    public Client(int id, String name, float cash) {
        this.id = id;
        this.name = name;
        this.cash = cash;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public float getCash() {
        return cash;
    }

    public int getId() {
        return id;
    }

}
