package com.example.newlook_hair_and_beauty_salon.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newlook_hair_and_beauty_salon.Helpers.CartHelper;
import com.example.newlook_hair_and_beauty_salon.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    List<CartHelper> cartHelperList;

    //
    public CartAdapter(Context context, List<CartHelper> cartHelperList) {
        this.context = context;
        this.cartHelperList = cartHelperList;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {

        holder.prodName.setText(cartHelperList.get(position).getProductName());
        holder.prodPrice.setText(cartHelperList.get(position).getProductPrice());
        holder.currentDate.setText(cartHelperList.get(position).getCurrentDate());
        holder.currentTime.setText(cartHelperList.get(position).getCurrentTime());
        holder.totalQnty.setText(cartHelperList.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(cartHelperList.get(position).getTotalPrice()));
    }

    @Override
    public int getItemCount() {
        return cartHelperList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView prodName, prodPrice, currentDate, currentTime, totalQnty, totalPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            prodName = itemView.findViewById(R.id.productname);
            prodPrice = itemView.findViewById(R.id.productprice);
            currentDate = itemView.findViewById(R.id.current_date);
            currentTime = itemView.findViewById(R.id.current_time);
            totalQnty = itemView.findViewById(R.id.total_quantity);
            totalPrice = itemView.findViewById(R.id.totalprice);
        }
    }
}
