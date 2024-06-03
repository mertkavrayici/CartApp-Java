package com.mertkavrayici.cartapp.products_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mertkavrayici.cartapp.MyApplication;
import com.mertkavrayici.cartapp.cart_page.CartActivity;
import com.mertkavrayici.cartapp.categories_page.CategoriesActivity;
import com.mertkavrayici.cartapp.categories_page.CategoryAdapter;
import com.mertkavrayici.cartapp.models.Product;
import com.mertkavrayici.cartapp.R;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter productsAdapter;
    private List<Product> productList;
    private MyApplication myApplication;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_products);
        myApplication = (MyApplication) getApplication();





        Toolbar toolbar = findViewById(R.id.productToolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        int productType = intent.getIntExtra("productType", 0);
        String appbarTitle = intent.getStringExtra("appbarTitle");
        getSupportActionBar().setTitle(appbarTitle);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPressed();
            }
        });

        productList = ((MyApplication) getApplication()).getProductList().stream().filter(product -> product.getProductType() == productType).collect(Collectors.toList());

        recyclerView = findViewById(R.id.productsRecyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView textView = findViewById(R.id.badgeTextView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);

                startActivityForResult(intent, productType);
            }
        });

        productsAdapter = new ProductAdapter(this, productList,textView);

        recyclerView.setAdapter(productsAdapter);


    }

    public void backPressed() {
        // Önceki ekrana geri dön
        super.onBackPressed();
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
        finish(); // Bu aktiviteyi sonlandırarak geri dönüşü sağlar
    }

}