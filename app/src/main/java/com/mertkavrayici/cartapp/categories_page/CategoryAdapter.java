package com.mertkavrayici.cartapp.categories_page;




import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mertkavrayici.cartapp.models.Category;
import com.mertkavrayici.cartapp.R;
import com.mertkavrayici.cartapp.products_page.ProductsActivity;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private static Context context;
    private static List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> productList) {
        this.context = context;
        categoryList = productList;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);

        holder.imageViewProduct.setImageDrawable(new BitmapDrawable(context.getResources(), category.getImagePath()));
        holder.textViewCategoryName.setText(category.getName());

    }



    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageViewProduct;
        TextView textViewCategoryName;


        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewCategory);
            textViewCategoryName = itemView.findViewById(R.id.textViewCategoryName);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Category clickedData = categoryList.get(position);
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("productType", clickedData.getProductType());
                intent.putExtra("appbarTitle",clickedData.getName());
                context.startActivity(intent);
            }
        }
    }
    }

