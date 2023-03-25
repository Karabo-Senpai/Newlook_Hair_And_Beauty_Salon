package com.example.newlook_hair_and_beauty_salon.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newlook_hair_and_beauty_salon.Classes.Bookings;
import com.example.newlook_hair_and_beauty_salon.R;

import java.util.ArrayList;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.ItemsViewHolder> {

    //Declaring Arraylist And Context Variables
    ArrayList<Bookings> bookings;
    Context context;

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointments, parent, false);
        ItemsViewHolder itemsViewHolder = new ItemsViewHolder(view);

        return itemsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingsAdapter.ItemsViewHolder holder, int position) {

        //Binding Items To View
        Bookings appointments = bookings.get(position);
        holder.first_name.setText(appointments.getFirst_name());
        holder.last_name.setText(appointments.getLast_name());
        holder.contact.setText(appointments.getContact());
        holder.service_type.setText(appointments.getService_type());
        holder.booking_date.setText(appointments.getAppointment_date().toString());
        holder.message.setText(appointments.getMessage());
    }

    //Counting Number Of Items
    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public BookingsAdapter(ArrayList<Bookings> bookingsArrayList, Context context) {

        bookings = bookingsArrayList;
        this.context = context;
    }


    public class ItemsViewHolder extends RecyclerView.ViewHolder {

        public TextView first_name, last_name, contact, service_type, booking_date, message;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);

            first_name = itemView.findViewById(R.id.user_name);
            last_name = itemView.findViewById(R.id.user_lname);
            contact = itemView.findViewById(R.id.user_contact);
            service_type = itemView.findViewById(R.id.service_type);
            booking_date = itemView.findViewById(R.id.booking_date);
            message = itemView.findViewById(R.id.user_message);
        }
    }
}
