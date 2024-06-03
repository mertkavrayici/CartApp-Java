package com.mertkavrayici.cartapp.cart_page;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mertkavrayici.cartapp.MainActivity;
import com.mertkavrayici.cartapp.MyApplication;
import com.mertkavrayici.cartapp.R;
import com.mertkavrayici.cartapp.categories_page.CategoriesActivity;
import com.mertkavrayici.cartapp.models.Product;
import com.mertkavrayici.cartapp.products_page.ProductAdapter;
import com.mertkavrayici.cartapp.products_page.ProductsActivity;

import java.util.List;
import java.util.stream.Collectors;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<Product> cartList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = findViewById(R.id.cartToolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backPressed();
            }
        });

        Button button = findViewById(R.id.sellButton);
                button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setMessage("Satın Alma İşlemi Gerçekleşti").setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ((MyApplication) getApplication()).clearCartList();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        cartList = ((MyApplication) getApplication()).getCartList();

        recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView sumText = findViewById(R.id.sumTextView);
        int toplamTutar = 0;
        for (Product product : cartList) {
            toplamTutar += product.getPrice();
        }
        sumText.setText(String.valueOf("Toplam Tutar" + toplamTutar + "TL"));


        cartAdapter = new CartAdapter(this, cartList, sumText);
        recyclerView.setAdapter(cartAdapter);
    }
    public void backPressed() {
        // Önceki ekrana geri dön
        super.onBackPressed();
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}