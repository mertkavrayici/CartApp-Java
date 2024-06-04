package com.mertkavrayici.cartapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.mertkavrayici.cartapp.categories_page.CategoriesActivity;
import com.mertkavrayici.cartapp.helper.DatabaseHelper;
import com.mertkavrayici.cartapp.models.Category;
import com.mertkavrayici.cartapp.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        TextView titleTextView = findViewById(R.id.titleTextView);

        ImageView imageView = findViewById(R.id.imageView);

        Button loginButton = findViewById(R.id.loginButton);
        addProducts();// Database'e kayıt atıyorum


        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });



    }
    public void addProducts(){
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        productList= ((MyApplication) getApplication()).getProductList();
        for(Product product : productList){
            dbHelper.addProduct(product);
        }


    }
}