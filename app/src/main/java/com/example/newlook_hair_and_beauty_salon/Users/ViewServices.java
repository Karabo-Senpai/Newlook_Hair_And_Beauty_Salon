package com.example.newlook_hair_and_beauty_salon.Users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.newlook_hair_and_beauty_salon.Adapters.AllProductsAdapter;
import com.example.newlook_hair_and_beauty_salon.Adapters.AllServicesAdapter;
import com.example.newlook_hair_and_beauty_salon.Classes.SalonProducts;
import com.example.newlook_hair_and_beauty_salon.Classes.SalonServices;
import com.example.newlook_hair_and_beauty_salon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewServices extends AppCompatActivity {

    /*
    GLOBAL VARIABLE DECLARATIONS
     */

    //Declaring Firebase Database Variables
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    //Creating Class Object For Salon Services Class
    SalonServices salonServices = new SalonServices();
    ImageView back;

    //Creating Object For Apdapter Class
    AllServicesAdapter allServicesAdapter;



    List<SalonServices> salonServicesList;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    //Creating Hooks For XML Linear Layout View Component
    LinearLayout contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_services);

        //Assigning Hooks To XML Components
        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.view_all_services_recycler);
        contentView = findViewById(R.id.content_view);
        back = findViewById(R.id.nav_back_btn);


        //Creating On Click For Back Icon Image To Revert To The Previous Screen
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),UserDashboard.class);
                startActivity(intent);

            }
        });


        //Calling Method To Retrieve Service Details From FireStore Database
        GetAllServices();


    }

    //Method To Retrieve All Service Information From Database
    private void GetAllServices() {

        //Setting Recycler View To Show Content That is Visible To The User
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        //Creating Array List Variable To Hold Favourite List items
        salonServicesList = new ArrayList<>();

        allServicesAdapter = new AllServicesAdapter(this, salonServicesList);

        //Passing Recycler View Items Into Respective Adapter Class

        recyclerView.setAdapter(allServicesAdapter);

        //  layoutManager = new LinearLayoutManager(this);

        firestore.collection("services")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                salonServices = document.toObject(SalonServices.class);
                                salonServicesList.add(salonServices);
                                allServicesAdapter.notifyDataSetChanged();
                            }
                        } else {

                            Toast.makeText(ViewServices.this, "Error " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }
}