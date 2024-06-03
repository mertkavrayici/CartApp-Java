package com.mertkavrayici.cartapp.models;

import android.graphics.Bitmap;

public class Product {
    private int id;
    private String name;
    private int price;
    private Bitmap imagePath;
    private int quantity;
    private int productType;


    // Tüm alanları içeren yapıcı metot
    public Product(int id, String name, int price, Bitmap imagePath, int quantity, int productType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.quantity = quantity;
        this.productType = productType;
    }

    // Getter ve setter metotları
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Bitmap getImagePath() {
        return imagePath;
    }

    public void setImagePath(Bitmap imagePath) {
        this.imagePath = imagePath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

}