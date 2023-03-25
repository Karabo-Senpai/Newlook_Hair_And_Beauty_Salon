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
import com.example.newlook_hair_and_beauty_salon.Helpers.ProductDetailsHelper;
import com.example.newlook_hair_and_beauty_salon.R;
import com.example.newlook_hair_and_beauty_salon.Users.ProductDetails;

import java.util.List;

public class ProductDetailsAdapter extends RecyclerView.Adapter<ProductDetailsAdapter.ViewHolder> {

    private Context context;
    List<ProductDetailsHelper> productDetailsHelperList;

    public ProductDetailsAdapter(Context context, List<ProductDetailsHelper> productDetailsHelperList) {
        this.context = context;
        this.productDetailsHelperList = productDetailsHelperList;
    }

    @NonNull
    @Override
    public ProductDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_products,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(productDetailsHelperList.get(position).getImage_url()).into(holder.prod_img);
        holder.prod_name.setText(productDetailsHelperList.get(position).getProductName());
        holder.prod_desc.setText(productDetailsHelperList.get(position).getProductDescription());
        holder.prod_price.setText(String.valueOf(productDetailsHelperList.get(position).getProductPrice()));
        holder.prod_availability.setText(productDetailsHelperList.get(position).getProductAvailability());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , ProductDetails.class);
                intent.putExtra("details",productDetailsHelperList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productDetailsHelperList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Creating Hooks For Recycler View Contents
        ImageView prod_img;
        TextView prod_name, prod_desc, prod_price, prod_availability;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            //Assigning Hooks To XML Components To Be Placed In Recycler View
            prod_img = itemView.findViewById(R.id.prod_img);
            prod_name = itemView.findViewById(R.id.prod_name);
            prod_desc = itemView.findViewById(R.id.prod_desc);
            prod_price = itemView.findViewById(R.id.prod_price);
            prod_availability = itemView.findViewById(R.id.prod_availability);

        }
    }
}
