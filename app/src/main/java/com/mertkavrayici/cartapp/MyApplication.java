package com.mertkavrayici.cartapp;


import android.app.Application;
import android.graphics.BitmapFactory;

import com.mertkavrayici.cartapp.models.Category;
import com.mertkavrayici.cartapp.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    private List<Category> categoryList;
    private List<Product> productList;

    private List<Product> cartList;

    @Override
    public void onCreate() {
        super.onCreate();

        categoryList = new ArrayList<>();
        categoryList.add(new Category("Pantolonlar", BitmapFactory.decodeResource(this.getResources(), R.drawable.panths), 1));
        categoryList.add(new Category("Ceketler", BitmapFactory.decodeResource(this.getResources(), R.drawable.jacket), 2));
        categoryList.add(new Category("Ayakkabılar", BitmapFactory.decodeResource(this.getResources(), R.drawable.shoes), 3));
        categoryList.add(new Category("Gömlekler", BitmapFactory.decodeResource(this.getResources(), R.drawable.skirts), 4));
        categoryList.add(new Category("Tişörtler", BitmapFactory.decodeResource(this.getResources(), R.drawable.tshirt), 5));
        categoryList.add(new Category("Aksesuarlar", BitmapFactory.decodeResource(this.getResources(), R.drawable.glass), 6));

        productList = new ArrayList<>();
        productList.add(new Product(0, "Pantolon 1", 50, BitmapFactory.decodeResource(this.getResources(), R.drawable.panths), 5, 1));
        productList.add(new Product(1, "Pantolon 2", 120, BitmapFactory.decodeResource(this.getResources(), R.drawable.panths), 3, 1));
        productList.add(new Product(2, "Pantolon 3", 180, BitmapFactory.decodeResource(this.getResources(), R.drawable.panths), 10, 1));
        productList.add(new Product(3, "Pantolon 4", 300, BitmapFactory.decodeResource(this.getResources(), R.drawable.panths), 17, 1));

        productList.add(new Product(4, "Ceket 1", 250, BitmapFactory.decodeResource(this.getResources(), R.drawable.jacket), 22, 2));
        productList.add(new Product(5, "Ceket 2", 480, BitmapFactory.decodeResource(this.getResources(), R.drawable.jacket), 8, 2));
        productList.add(new Product(6, "Ceket 3", 720, BitmapFactory.decodeResource(this.getResources(), R.drawable.jacket), 2, 2));
        productList.add(new Product(7, "Ceket 4", 1020, BitmapFactory.decodeResource(this.getResources(), R.drawable.jacket), 19, 2));

        productList.add(new Product(8, "Ayakkabı 1", 720, BitmapFactory.decodeResource(this.getResources(), R.drawable.shoes), 100, 3));
        productList.add(new Product(9, "Ayakkabı 2", 1000, BitmapFactory.decodeResource(this.getResources(), R.drawable.shoes), 200, 3));
        productList.add(new Product(10, "Ayakkabı 3", 1200, BitmapFactory.decodeResource(this.getResources(), R.drawable.shoes), 21, 3));

        productList.add(new Product(11, "Gömlek 1", 120, BitmapFactory.decodeResource(this.getResources(), R.drawable.skirts), 2, 4));
        productList.add(new Product(12, "Gömlek 2", 75, BitmapFactory.decodeResource(this.getResources(), R.drawable.skirts), 1, 4));
        productList.add(new Product(13, "Gömlek 3", 220, BitmapFactory.decodeResource(this.getResources(), R.drawable.skirts), 19, 4));

        productList.add(new Product(14, "Tişört 1", 90, BitmapFactory.decodeResource(this.getResources(), R.drawable.tshirt), 111, 5));
        productList.add(new Product(15, "Tişört 2", 120, BitmapFactory.decodeResource(this.getResources(), R.drawable.tshirt), 222, 5));
        productList.add(new Product(16, "Tişört 3", 280, BitmapFactory.decodeResource(this.getResources(), R.drawable.tshirt), 3, 5));
        productList.add(new Product(17, "Tişört 4", 320, BitmapFactory.decodeResource(this.getResources(), R.drawable.tshirt), 320, 5));
        productList.add(new Product(18, "Tişört 5", 210, BitmapFactory.decodeResource(this.getResources(), R.drawable.tshirt), 210, 5));

        productList.add(new Product(19, "Gözlük 1", 300, BitmapFactory.decodeResource(this.getResources(), R.drawable.glass), 11, 6));

        productList.add(new Product(20, "Gözlük 2", 500, BitmapFactory.decodeResource(this.getResources(), R.drawable.glass), 12, 6));

        cartList =new ArrayList<>();

    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public List<Product> getCartList(){
        return cartList;
    }
    public void addProductToCartList(Product product){
        cartList.add(product);
    }
    public  void  removeProductToCartList(Product product){
        cartList.remove(product);
    }
    public void clearCartList(){
        cartList.clear();
    }

}

