package com.example.newlook_hair_and_beauty_salon.Users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newlook_hair_and_beauty_salon.Adapters.BookingsAdapter;
import com.example.newlook_hair_and_beauty_salon.Classes.Bookings;
import com.example.newlook_hair_and_beauty_salon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Appointments extends AppCompatActivity {


    //Declaring Firebase Database Variables
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    Bookings bookings, items;
    //Declaring Saved Item Details Variables
    String name, sname, contact, service, message, booking_date;
    ImageView back;

    //Declaring List View And Recycler View
    List<Bookings> bookingsList;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        databaseReference = firebaseDatabase.getReference(mAuth.getCurrentUser().getUid());
        back = findViewById(R.id.back_btn);

        //Creating Array List Variable To Hold Favourite List items
        ArrayList<Bookings> bookings1 = new ArrayList<>();

        layoutManager = new LinearLayoutManager(this);

        databaseReference.child("appointments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                bookings = new Bookings();

                for (DataSnapshot savedItems : snapshot.getChildren()) {

                    bookings = savedItems.getValue(Bookings.class);
                    assert bookings != null;

                    items = new Bookings(bookings.first_name, bookings.last_name, bookings.contact, bookings.service_type, bookings.appointment_date, bookings.confirmation_method, bookings.message);
                    bookings1.add(items);
                }

                //Try And Catch To Handle Any Error That May Occur
                try {
                    recyclerView = findViewById(R.id.appointments_recycler);
                    recyclerView.setHasFixedSize(true);
                    Collections.reverse(bookings1);
                    recycler_adapter = new BookingsAdapter(bookings1,Appointments.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(recycler_adapter);
                }
                catch (Exception e){
                    Toast.makeText(Appointments.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Appointments.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),UserDashboard.class);
                startActivity(intent);

            }
        });

    }
}