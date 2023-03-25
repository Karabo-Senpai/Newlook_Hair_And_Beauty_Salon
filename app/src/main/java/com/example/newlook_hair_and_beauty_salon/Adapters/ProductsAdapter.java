package com.example.newlook_hair_and_beauty_salon.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newlook_hair_and_beauty_salon.Helpers.ProductsHelper;
import com.example.newlook_hair_and_beauty_salon.R;
import com.example.newlook_hair_and_beauty_salon.Users.ViewProducts;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.FeaturedViewHolder> {

    //Creating Arraylist To Hold Product Recycler View Items
    private  Context context;
    List<ProductsHelper> productList;

    public ProductsAdapter(Context context, List<ProductsHelper> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductsAdapter.FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products,parent,false);

        //Passing View To View Holder
        ProductsAdapter.FeaturedViewHolder featuredViewHolder = new ProductsAdapter.FeaturedViewHolder(view);

        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.FeaturedViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //Creating Object For Product Helper Class
        Glide.with(context).load(productList.get(position).getImage_url()).into(holder.image);
        holder.product_name.setText(productList.get(position).getProductName());
        holder.product_desc.setText(productList.get(position).getProductDescription());
        holder.product_price.setText(productList.get(position).getProductPrice());
        holder.product_availability.setText(productList.get(position).getProductAvailability());
        holder.product_type.setText(productList.get(position).getProduct_type());

        //Creating Onclick Listener For When Product Recycler View Is Clicked Pressed
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ViewProducts.class);
                intent.putExtra("product_type",productList.get(position).getProduct_type());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    //
    public static class FeaturedViewHolder extends  RecyclerView.ViewHolder{

        //Declaring Hooks For XML Components
        ImageView image;
        TextView product_name,product_desc,product_price, product_availability,product_type;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Assigning Hooks To Respective XML Components
            image = itemView.findViewById(R.id.product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_desc = itemView.findViewById(R.id.product_description);
            product_price = itemView.findViewById(R.id.product_price);
            product_availability = itemView.findViewById(R.id.product_availability);
            product_type = itemView.findViewById(R.id.product_type);
        }
    }

}
