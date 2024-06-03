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
import com.mertkavrayici.cartapp.models.Category;
import com.mertkavrayici.cartapp.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Category> categoryList;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        categoryList = new ArrayList<>();
        categoryList.add(new Category("Pantolonlar", BitmapFactory.decodeResource(this.getResources(), R.drawable.panths),1));
        categoryList.add(new Category("Ceketler",BitmapFactory.decodeResource(this.getResources(), R.drawable.jacket),2));
        categoryList.add(new Category("Ayakkabılar",BitmapFactory.decodeResource(this.getResources(), R.drawable.shoes),3));
        categoryList.add(new Category("Gömlekler",BitmapFactory.decodeResource(this.getResources(), R.drawable.skirts),4));
        categoryList.add(new Category("Tişörtler",BitmapFactory.decodeResource(this.getResources(), R.drawable.tshirt),5));
        categoryList.add(new Category("Aksesuarlar",BitmapFactory.decodeResource(this.getResources(), R.drawable.glass),6));

        TextView titleTextView = findViewById(R.id.titleTextView);

        ImageView imageView = findViewById(R.id.imageView);

        Button loginButton = findViewById(R.id.loginButton);


        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });


    }
    public List<Category> getCategoryList(){
        return categoryList;
    }
}