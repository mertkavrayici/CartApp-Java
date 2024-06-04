package com.mertkavrayici.cartapp.cart_page;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.mertkavrayici.cartapp.MyApplication;
import com.mertkavrayici.cartapp.R;
import com.mertkavrayici.cartapp.models.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private static Context context;
    private static List<Product> cartList;

    private TextView textView;

    public CartAdapter(Context context, List<Product> cartList,TextView textView) {
        this.context = context;
        this.cartList = cartList;
        this.textView =textView;
    }


    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        Product product = cartList.get(position);

        holder.cartImageView.setImageResource(R.drawable.mall);
        holder.textViewCartName.setText(product.getName());
        holder.textViewCartPrice.setText(String.valueOf("Fiyat :"+product.getPrice() +" TL"));
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication myApp = (MyApplication) v.getContext().getApplicationContext();
                myApp.removeProductToCartList(cartList.get(position));
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,cartList.size());
                Snackbar.make(v, "Ürün Sepetten kaldırıldı" , Snackbar.LENGTH_SHORT).show();
                int toplamTutar =0;
                for(Product product :cartList){
                    toplamTutar += product.getPrice();
                }
                textView.setText(String.valueOf("Toplam Tutar" + toplamTutar + "TL"));





            }

        });

    }



    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView cartImageView;
        TextView textViewCartName;

        TextView textViewCartPrice;

        ImageView deleteButton;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartImageView = itemView.findViewById(R.id.cartImageView);
            textViewCartName = itemView.findViewById(R.id.textViewCartName);
            textViewCartPrice = itemView.findViewById(R.id.textViewCartPrice);
            deleteButton = itemView.findViewById(R.id.deleteButton);

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


