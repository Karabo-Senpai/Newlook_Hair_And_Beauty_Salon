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

import com.example.newlook_hair_and_beauty_salon.Helpers.ServiceHelper;
import com.example.newlook_hair_and_beauty_salon.R;
import com.example.newlook_hair_and_beauty_salon.Users.ViewProducts;
import com.example.newlook_hair_and_beauty_salon.Users.ViewServices;

import java.util.ArrayList;

//Extending Recycler View Class To Gain Access To It's Items
public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    //Array List To Hold Service Recycler View Items

    private Context context;
    ArrayList<ServiceHelper> serviceList;

    public ServiceAdapter(Context context, ArrayList<ServiceHelper> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceAdapter.ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Creating Layout Inflater For The Recycler View
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.our_services, parent,false);

        //Passing View To View Holder
        ServiceViewHolder serviceViewHolder = new ServiceViewHolder(view);

        //Returning View Holder To Be Displayed On The Recycler View
        return serviceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ServiceViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //Getting Current Position Of Recycler View Item
        ServiceHelper serviceHelper = serviceList.get(position);

        //Binding Items To The Holder
        holder.image.setImageResource(serviceHelper.getImage());
        holder.service_name.setText(serviceHelper.getService_name());
        holder.service_1.setText(serviceHelper.getService_1());
        holder.service_2.setText(serviceHelper.getService_2());


        //Creating Onclick Listener For When Product Recycler View Is Clicked Pressed
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ViewServices.class);
                intent.putExtra("service_name",serviceList.get(position).getService_name());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {

        //Declaring Variables To Hold Items To Be Bound Within A Holder In The Recycler View
        ImageView image;
        TextView service_name, service_1, service_2;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            //Assigning Hooks To Respective XML Components To Be Used In The Bind
            image = itemView.findViewById(R.id.service_image);
            service_name = itemView.findViewById(R.id.service_name);
            service_1 = itemView.findViewById(R.id.service_1);
            service_2 = itemView.findViewById(R.id.service_2);

        }
    }
}
