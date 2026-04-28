package com.example.ecommerceclonejava.model;

public class Product {
    public String name, price, desc;
    public int image;

    public Product(String name, String price, String desc, int image) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.image = image;
    }
}