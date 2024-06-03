package com.mertkavrayici.cartapp.products_page;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.mertkavrayici.cartapp.MyApplication;
import com.mertkavrayici.cartapp.R;
import com.mertkavrayici.cartapp.models.Category;
import com.mertkavrayici.cartapp.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private static Context context;
    private static List<Product> productList;
    private TextView cartText;







    public ProductAdapter(Context context, List<Product> productList,TextView cartText) {
        this.context = context;
        this.productList = productList;
        this.cartText = cartText;



    }


    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.imageViewProduct.setImageDrawable(new BitmapDrawable(context.getResources(), product.getImagePath()));
        holder.textViewProductName.setText(product.getName());
        holder.textViewProductPrice.setText(String.valueOf("Fiyat :"+product.getPrice() +" TL"));



        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication myApp = (MyApplication) v.getContext().getApplicationContext();
                myApp.addProductToCartList(productList.get(position));
                cartText.setText(String.valueOf(myApp.getCartList().size()));
                Snackbar.make(v, "Ürün Başarıyla Sepete Eklendi" , Snackbar.LENGTH_SHORT).show();





            }

        });


    }



    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;

        TextView textViewProductPrice;
        ImageButton imageButton;

        TextView textView;
        TextView cartText;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.productImageView);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            textViewProductPrice = itemView.findViewById(R.id.textViewProductPrice);
            imageButton =itemView.findViewById(R.id.add_button);
            cartText =itemView.findViewById(R.id.badgeTextView);
            //itemView.setOnClickListener(this);

        }

      /*  @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Product clickedData = productList.get(position);
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("productType", clickedData.getProductType());
                context.startActivity(intent);
            }
        }*/
    }


}


