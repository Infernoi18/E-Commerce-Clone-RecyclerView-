package com.example.ecommerceclonejava.model;

public class CartItem {

    public String name;
    public String price;
    public int image;
    public int quantity;

    public CartItem(String name, String price, int image, int quantity) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }
}