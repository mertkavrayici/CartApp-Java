package com.mertkavrayici.cartapp.models;

import android.graphics.Bitmap;

public class Category {

    private String name;
    private Bitmap imagePath;

    private Integer productType;

    // Constructor
    public Category(String name, Bitmap imagePath,  Integer productType) {

        this.name = name;

        this.imagePath = imagePath;
        this.productType = productType;
    }

    // Getters and Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    public Bitmap getImagePath() {
        return imagePath;
    }

    public void setImagePath(Bitmap imagePath) {
        this.imagePath = imagePath;
    }


    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }
}