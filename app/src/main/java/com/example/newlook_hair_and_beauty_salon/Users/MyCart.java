package com.example.newlook_hair_and_beauty_salon.Users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.newlook_hair_and_beauty_salon.Adapters.AllProductsAdapter;
import com.example.newlook_hair_and_beauty_salon.Adapters.CartAdapter;
import com.example.newlook_hair_and_beauty_salon.Classes.SalonProducts;
import com.example.newlook_hair_and_beauty_salon.Helpers.CartHelper;
import com.example.newlook_hair_and_beauty_salon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyCart extends AppCompatActivity {


    //Declaring Firebase Database Variables
    FirebaseFirestore firestore;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    //Creating Class Object For Salon Products Class
    SalonProducts salonProducts = new SalonProducts();
    ImageView back;

    //Creating Object For AllProducts Adapter
    CartAdapter cartAdapter;
    List<CartHelper> cartHelperList;
    RecyclerView recyclerView;
    RecyclerView.Adapter recycler_adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);


        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.cart_recycler);
        mAuth = FirebaseAuth.getInstance();
        // contentView = findViewById(R.id.content_view);

        //Setting Recycler View To Show Content That is Visible To The User
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        cartHelperList = new ArrayList<>();
        cartAdapter = new CartAdapter(this, cartHelperList);

        recyclerView.setAdapter(cartAdapter);

        firestore.collection("cartload").document(mAuth.getCurrentUser().getUid())
                .collection("currentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful()){
                            for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                                CartHelper cartHelper = documentSnapshot.toObject(CartHelper.class);
                                cartHelperList.add(cartHelper);
                                cartAdapter.notifyDataSetChanged();
                            }
                        }


                    }
                });


    }
}