package com.gmailat.pm.entity;

public class Product {

    private int id;
    private String name;
    private String category;
    private float price;
    private int discount;

    public Product(int id, String name, String category, float price, int discount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.discount = discount;
    }

    public float getDiscountBenefit() {
        if(discount == 0) {
            return discount;
        }
        return  price * discount / 100;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getDiscount() {
        return discount;
    }

    public float getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
