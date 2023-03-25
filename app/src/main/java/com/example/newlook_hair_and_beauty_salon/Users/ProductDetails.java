package com.example.newlook_hair_and_beauty_salon.Users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newlook_hair_and_beauty_salon.Classes.SalonProducts;
import com.example.newlook_hair_and_beauty_salon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetails extends AppCompatActivity {


    /*
      Global Variable Declarations
     */

    //Declaring Text View To Hold Placeholder Value For Elegant Number Button
    TextView productQuantity;
    int totalQuantity = 1;
    int totalPrice = 0;

    //Declaring Firebase Variables
    FirebaseDatabase firebaseDatabase;
    FirebaseFirestore firebaseFirestore;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    //Declaring Hooks
    ImageView product_image, add_item, remove_item;
    TextView product_name, product_desc, product_price;
    Button addToCart;
    Toolbar toolbar;
    SalonProducts salonProducts = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        //Instantiating Firebase Declared Variables
        mAuth = FirebaseAuth.getInstance();
       // firebaseUser = mAuth.getCurrentUser();
        //firebaseDatabase = FirebaseDatabase.getInstance();
      //  DatabaseReference databaseReference = firebaseDatabase.getReference(mAuth.getCurrentUser().getUid());

        firebaseFirestore = FirebaseFirestore.getInstance();


        //Creating Object
        final Object object = getIntent().getSerializableExtra("details");

        if (object instanceof SalonProducts) {

            salonProducts = (SalonProducts) object;
        }

        //Assigning Hooks
        product_image = findViewById(R.id.prodct_img);
        add_item = findViewById(R.id.add_items);
        remove_item = findViewById(R.id.remove_item);
        product_name = findViewById(R.id.prodct_name);
        product_desc = findViewById(R.id.prodct_desc);
        product_price = findViewById(R.id.prodct_price);
        addToCart = findViewById(R.id.add_cart_btn);
        productQuantity = findViewById(R.id.item_quantity);

        if (salonProducts != null) {

            Glide.with(getApplicationContext()).load(salonProducts.getImage_url()).into(product_image);
            product_name.setText(salonProducts.getProductName());
            product_price.setText(salonProducts.getProductPrice());
            product_desc.setText(salonProducts.getProductDescription());




        }


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ProductsInCart();

            }
        });

        //
        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (totalQuantity < 20) {
                    //Incrementing Quantity
                    totalQuantity++;
                    productQuantity.setText(String.valueOf(totalQuantity));
                  //  totalPrice = Integer.parseInt(salonProducts.getProductPrice().toString()) * totalQuantity;
                }
            }
        });

        remove_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (totalQuantity > 1) {

                    //Incrementing Quantity
                    totalQuantity--;
                    productQuantity.setText(String.valueOf(totalQuantity));
                   // totalPrice = Integer.parseInt(salonProducts.getProductPrice().toString()) * totalQuantity;

                }

            }
        });


    }

    private void ProductsInCart() {

        String saveDate, saveTime;

        Calendar callForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");

        saveDate = currentDate.format(callForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH :mm:ss a");

        saveTime = currentTime.format(callForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName",salonProducts.getProductName());
        cartMap.put("productPrice",product_price.getText().toString());
        cartMap.put("currentDate",saveDate);
        cartMap.put("currentTime",saveTime);
        cartMap.put("totalQuantity", productQuantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        firebaseFirestore.collection("cartload").document(mAuth.getCurrentUser().getUid()).collection("currentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

                Toast.makeText(ProductDetails.this, "Successfully Added To Cart", Toast.LENGTH_SHORT).show();

                 finish();

            }
        });



    }
}