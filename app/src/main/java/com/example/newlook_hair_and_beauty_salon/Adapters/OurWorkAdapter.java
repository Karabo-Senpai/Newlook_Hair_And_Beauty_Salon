package com.example.newlook_hair_and_beauty_salon.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newlook_hair_and_beauty_salon.Helpers.OurWorkHelper;
import com.example.newlook_hair_and_beauty_salon.R;

import java.util.ArrayList;

public class OurWorkAdapter extends RecyclerView.Adapter<OurWorkAdapter.FeaturedViewHolder> {


    //Creating Array List For The Helper Class
    ArrayList<OurWorkHelper> ourWorkHelper;

    //Creating Non Default Constructor
    public OurWorkAdapter(ArrayList<OurWorkHelper> workHelpers) {

        this.ourWorkHelper = workHelpers;
    }

    @NonNull
    @Override
    public OurWorkAdapter.FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.our_work, parent, false);

        //Passing View To View Holder
        OurWorkAdapter.FeaturedViewHolder featuredViewHolder = new OurWorkAdapter.FeaturedViewHolder(view);

        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OurWorkAdapter.FeaturedViewHolder holder, int position) {

        //Creating Object For Our Work Helper Class
        OurWorkHelper workHelper = ourWorkHelper.get(position);

        //
        holder.image.setImageResource(workHelper.getImage());
        holder.work_name.setText(workHelper.getWork_name());
        holder.work_price.setText(workHelper.getPrice());

    }

    @Override
    public int getItemCount() {
        return ourWorkHelper.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {

        //Declaring Hooks For XML Components
        ImageView image;
        TextView work_name, work_price;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Assigning Hooks To Respective XML Components
            image = itemView.findViewById(R.id.work_image);
            work_name = itemView.findViewById(R.id.work_name);
            work_price = itemView.findViewById(R.id.work_price);
        }
    }

}
