package com.example.administrator.myapka;

public class Product {
    public int id;
    public String name;
    public double price;
    public int quantity;
    public boolean bought;

    public Product() {

    }

    public Product(int id, String name, double price, int quantity, boolean bought) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.bought = bought;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
