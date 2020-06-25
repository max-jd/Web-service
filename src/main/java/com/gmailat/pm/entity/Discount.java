package com.gmailat.pm.entity;

public class Discount {

    private int id;
    private int percent;

    public Discount(int id, int percent) {
        this.id = id;
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public int getPercent() {
        return percent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

}
