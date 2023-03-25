package com.example.newlook_hair_and_beauty_salon.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newlook_hair_and_beauty_salon.Classes.SalonProducts;
import com.example.newlook_hair_and_beauty_salon.Classes.SalonServices;
import com.example.newlook_hair_and_beauty_salon.R;

import java.util.List;

public class AllServicesAdapter extends RecyclerView.Adapter<AllServicesAdapter.ViewHolder> {

    //Creating Array List To Get Declared Variables To Hold Product Data In Recycler View
    private Context context;
    private List<SalonServices> salonServicesList;

    public AllServicesAdapter(Context context, List<SalonServices> salonServicesList) {
        this.context = context;
        this.salonServicesList = salonServicesList;
    }

    @NonNull
    @Override
    public AllServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Returning View All Service Layout View
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_services,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AllServicesAdapter.ViewHolder holder, int position) {


        Glide.with(context).load(salonServicesList.get(position).getImage_url()).into(holder.service_img);
        holder.service_type.setText(salonServicesList.get(position).getServiceType());
        holder.service_desc.setText(salonServicesList.get(position).getServiceDescription());
        holder.service_price.setText(salonServicesList.get(position).getServicePrice());


    }

    @Override
    public int getItemCount() {
        return salonServicesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Creating Hooks For Recycler View Contents
        ImageView service_img;
        TextView service_type, service_desc, service_price;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Assigning Hooks To XML Components To Be Placed In Recycler View
            service_img = itemView.findViewById(R.id.service_img);
            service_type = itemView.findViewById(R.id.service_type);
            service_desc = itemView.findViewById(R.id.service_desc);
            service_price = itemView.findViewById(R.id.service_price);

        }
    }
}
