package com.example.newlook_hair_and_beauty_salon.Users;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.newlook_hair_and_beauty_salon.Adapters.AllProductsAdapter;
import com.example.newlook_hair_and_beauty_salon.Adapters.OurWorkAdapter;
import com.example.newlook_hair_and_beauty_salon.Classes.Bookings;
import com.example.newlook_hair_and_beauty_salon.Classes.SalonProducts;
import com.example.newlook_hair_and_beauty_salon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ViewProducts extends AppCompatActivity {

    //Declaring Firebase Database Variables
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    //Creating Class Object For Salon Products Class
    SalonProducts salonProducts = new SalonProducts();
    ImageView back;


    //Creating Object For AllProducts Adapter
    AllProductsAdapter allProductsAdapter;
    List<SalonProducts> salonProductsList;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;
    //Creating Hooks For XML Linear Layout View Component
    LinearLayout contentView;
    String product_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);


        //Instantiating Declared Variable
        product_type = getIntent().getStringExtra("productName");

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.view_all_products_recycler);
        contentView = findViewById(R.id.content_view);
        back = findViewById(R.id.nav_back_icon);


        //Creating On Click For Back Icon Image To Revert To The Previous Screen
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                startActivity(intent);

            }
        });


        //Calling Method To Retrieve Products From FireStore Database
        GetProducts();

       // GetProductType();

    }

    public void GetProducts() {

        //Setting Recycler View To Show Content That is Visible To The User
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        //Creating Array List Variable To Hold Favourite List items
        salonProductsList = new ArrayList<>();

        allProductsAdapter = new AllProductsAdapter(this, salonProductsList);

        //Passing Recycler View Items Into Respective Adapter Class

        recyclerView.setAdapter(allProductsAdapter);

        //  layoutManager = new LinearLayoutManager(this);

        firestore.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                salonProducts = document.toObject(SalonProducts.class);
                                salonProductsList.add(salonProducts);
                                allProductsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(ViewProducts.this, "Error " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void GetProductType() {


        //Setting Recycler View To Show Content That is Visible To The User
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        //Creating Array List Variable To Hold Favourite List items
        salonProductsList = new ArrayList<>();

        allProductsAdapter = new AllProductsAdapter(this, salonProductsList);

        //Passing Recycler View Items Into Respective Adapter Class

        recyclerView.setAdapter(allProductsAdapter);


        if (product_type != null && product_type.equalsIgnoreCase("products")) {

            firestore.collection("products").whereEqualTo("product_type", "Hair Food").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {


                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        SalonProducts salonProducts = documentSnapshot.toObject(SalonProducts.class);

                        salonProductsList.add(salonProducts);
                        allProductsAdapter.notifyDataSetChanged();


                    }
                }
            });
        } else if (product_type != null && product_type.equalsIgnoreCase("products")) {

            firestore.collection("products").whereEqualTo("product_type", "Accessories").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {


                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        SalonProducts salonProducts = documentSnapshot.toObject(SalonProducts.class);

                        salonProductsList.add(salonProducts);
                        allProductsAdapter.notifyDataSetChanged();

                    }
                }
            });

        } else if (product_type != null && product_type.equalsIgnoreCase("products")) {

            firestore.collection("products").whereEqualTo("product_type", "Shampoos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {


                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        SalonProducts salonProducts = documentSnapshot.toObject(SalonProducts.class);

                        salonProductsList.add(salonProducts);
                        allProductsAdapter.notifyDataSetChanged();
                        //Hair Oil
                    }
                }
            });

        } else if (product_type != null && product_type.equalsIgnoreCase("products")) {

            firestore.collection("products").whereEqualTo("product_type", "Hair Oil  ").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                        SalonProducts salonProducts = documentSnapshot.toObject(SalonProducts.class);
                        salonProductsList.add(salonProducts);
                        allProductsAdapter.notifyDataSetChanged();
                        //
                    }
                }
            });

        }

    }
}