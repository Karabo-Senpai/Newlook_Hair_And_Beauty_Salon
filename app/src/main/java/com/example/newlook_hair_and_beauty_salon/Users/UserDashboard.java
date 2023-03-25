package com.example.newlook_hair_and_beauty_salon.Users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newlook_hair_and_beauty_salon.Adapters.AllProductsAdapter;
import com.example.newlook_hair_and_beauty_salon.Adapters.OurWorkAdapter;
import com.example.newlook_hair_and_beauty_salon.Adapters.ProductsAdapter;
import com.example.newlook_hair_and_beauty_salon.Adapters.ServiceAdapter;
import com.example.newlook_hair_and_beauty_salon.Classes.SalonProducts;
import com.example.newlook_hair_and_beauty_salon.Helpers.IntentHelper;
import com.example.newlook_hair_and_beauty_salon.Helpers.OurWorkHelper;
import com.example.newlook_hair_and_beauty_salon.Helpers.ProductsHelper;
import com.example.newlook_hair_and_beauty_salon.Helpers.ServiceHelper;
import com.example.newlook_hair_and_beauty_salon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /*
      GLOBAL VARIABLE DECLARATIONS
     */

    //Creating Object For Intent Helper Class
    IntentHelper intentHelper = new IntentHelper();

    //Variable to move screen scale to the side when navigation menu is drawn
    static final float END_SCALE = 0.7f;

    //Creating Firebase Variables
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth;
    FirebaseUser current_user;
    //Declaring Firebase Database Variables
    FirebaseFirestore firestore;

    //Creating Class Object For Products Helper Class
    ProductsHelper productsHelper = new ProductsHelper();

    //Creating Object For Products Adapter
    ProductsAdapter productsAdapter;
    List<ProductsHelper> productsHelperList;


    //Alert Dialog
    AlertDialog alertDialog;

    //Creating Variables For XML Hook Components
    RecyclerView recyclerView, recyclerView2, recyclerView3;
    RecyclerView.Adapter adapter;
    //Image View For Menu Button For When Pressed
    ImageView menu_icon, booking;

    TextView viewAll;

    //Creating Hooks For XML Drawer Layout Component
    DrawerLayout drawerLayout;

    //Creating Hooks For XML Navigation View Component
    NavigationView navigationView;

    //Creating Hooks For XML Linear Layout View Component
    LinearLayout contentView;

    List<SalonProducts> salonProductsList;

    String product_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        //Assigning Declared Variables To Their Respective Hooks
        recyclerView = findViewById(R.id.service_recycler);
        recyclerView2 = findViewById(R.id.products_recycler);
        recyclerView3 = findViewById(R.id.some_work_recycler);
        menu_icon = findViewById(R.id.nav_menu_icon);
        contentView = findViewById(R.id.content);
        booking = findViewById(R.id.book_appointment);
        viewAll = findViewById(R.id.view_all_products);

        AllProductsAdapter allProductsAdapter;


        //Instantiating Declared Variable
        product_type = getIntent().getStringExtra("productName");

        firestore = FirebaseFirestore.getInstance();

        //Assigning Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        //Assigning Declared Variables
        mAuth = FirebaseAuth.getInstance();
        current_user = mAuth.getCurrentUser();

        //Calling Method To Open Navigation Menu
        OpenNavigationDrawer();

        //Calling Method For Featured Services Offered By The Client
        OurServices();

        //Calling Method For Show All Products For Sale Offered By The Client
        ProductsRecycler();

        // salonProductsList = new ArrayList<>();

        //  allProductsAdapter = new AllProductsAdapter(this , salonProductsList);

        //Calling Method For Show All Previous Work
        OurWork();


        //Setting Onclick Listener For When View All Text Is Clicked
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewProducts.class);
                startActivity(intent);
            }
        });


        //Moving User To The Book Appointment Screen When Appointment Tab Section Is Clicked
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Appointments.class);

                //Moving User To The Book Appointment Screen
                startActivity(intent);
            }
        });

    }

    private void OurWork() {

        //Setting Recycler View To Show Content That is Visible To The User
        recyclerView3.setHasFixedSize(true);

        //Creating Recycler View Layout Orientation
        recyclerView3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //Passing Array To The Design Adapter
        ArrayList<OurWorkHelper> our_work = new ArrayList<>();

        //Passing Data Into Array List
        our_work.add(new OurWorkHelper(R.drawable.whatsapp_image_2022_10_16_at_14_17_52, "Low Taper Fade And Black Hair Coloring", "R 120.00"));
        our_work.add(new OurWorkHelper(R.drawable.whatsapp_image_2022_10_16_at_14_17_54, "Pink Hair Coloring / Dye", "R 80.00"));
        our_work.add(new OurWorkHelper(R.drawable.whatsapp_image_2022_10_16_at_14_17_48, "S-Curl Hair Cut", "R 75.00"));
        our_work.add(new OurWorkHelper(R.drawable.whatsapp_image_2022_10_16_at_14_17_55, "Hair Cut", "R 50.00"));

        //Passing Recycler View Items Into Respective Adapter Class
        adapter = new OurWorkAdapter(our_work);
        //Passing Items To Be Displayed In Services Recycler View
        recyclerView3.setAdapter(adapter);

    }

    //Method To Display Products In Recycler View
    private void ProductsRecycler() {

        //Setting Recycler View To Show Content That is Visible To The User
        recyclerView2.setHasFixedSize(true);

        recyclerView2.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        //Creating Array List Variable To Hold Favourite List items
        productsHelperList = new ArrayList<>();

        productsAdapter = new ProductsAdapter(this, productsHelperList);

        //Passing Recycler View Items Into Respective Adapter Class

        recyclerView2.setAdapter(productsAdapter);

        //  layoutManager = new LinearLayoutManager(this);

        firestore.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                productsHelper = document.toObject(ProductsHelper.class);
                                productsHelperList.add(productsHelper);
                                productsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(UserDashboard.this, "Error " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //Method To Display Services In Recycler View
    private void OurServices() {

        //Setting Recycler View To Show Content That is Visible To The User
        recyclerView.setHasFixedSize(true);

        //Creating Recycler View Layout Orientation
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //Passing Array To The Design Adapter
        ArrayList<ServiceHelper> services = new ArrayList<>();

        //Adding Data To Array List
        services.add(new ServiceHelper(R.drawable.hair_care, "Hair Care", "Haircuts & Styling Men Women And Children", "Conditioning Color & Highlights"));
        services.add(new ServiceHelper(R.drawable.nail_care, "Nail Care", "Pedicure & Manicure", "Acrylic Nail"));
        services.add(new ServiceHelper(R.drawable.waxing, "Waxing", "Body Waxing", "Face Waxing"));
        services.add(new ServiceHelper(R.drawable.facial, "Facial Treatment", "Basic Facials & Deluxe Facials", "Moisturizing Facials & Acne Treatments"));

        //Passing Recycler View Items Into Respective Adapter Class
        adapter = new ServiceAdapter(this, services);
        //Passing Items To Be Displayed In Services Recycler View
        recyclerView.setAdapter(adapter);

    }
    private void OpenNavigationDrawer() {

        //Making Navigation View Clickable
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        //Setting On Click Listener For Menu Icon
        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {

                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {

                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        AnimateNavigationDrawer();
    }

    //Method To Animate Navigation Drawer So Its Moved To The Side When Navigation Drawer Is Opened
    private void AnimateNavigationDrawer() {

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scaling Navigation View Based On Slide Offset in Current Effect
                final float diff_Scaled_Offset = slideOffset * (1 - END_SCALE);
                final float offset_Scale = 1 - diff_Scaled_Offset;
                contentView.setScaleX(offset_Scale);
                contentView.setScaleY(offset_Scale);

                //Translating View Based On Device Scaled Width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diff_Scaled_Offset / 2;
                final float x_translation = xOffset - xOffsetDiff;
                contentView.setTranslationX(x_translation);

            }

        });

    }

    //Method To Close Navigation Menu Instead Of Application When Navigation Drawer Is Closed
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Method To Get Selected Navigation Item
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //Setting Navigation Menu Screen Changing
        switch (item.getItemId()) {
            //Directing User To The Home Screen
            case R.id.nav_home:
                intentHelper.Nav_Open_Intent(this, UserDashboard.class);
                break;
            //Case Should The User Want To Logout of The Application
            case R.id.nav_logout:
                Toast.makeText(this, " " + mAuth.getCurrentUser().getEmail() + "Has Successfully Logged Out", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                finishAffinity();
                startActivity(new Intent(getApplicationContext(), UserLogin.class));
                break;

            //Moving User To The Booking Screen From The Navigation Menu
            case R.id.nav_book:
                intentHelper.Nav_Open_Intent(this, Book_Appointment.class);
                break;

            case R.id.nav_profile:
                intentHelper.Nav_Open_Intent(this, ProfileSettings.class);
                break;

            case R.id.nav_products:
                intentHelper.Nav_Open_Intent(this, ViewProducts.class);
                break;

            case R.id.nav_services:
                intentHelper.Nav_Open_Intent(this, ViewServices.class);
                break;

            case R.id.nav_cart:
                intentHelper.Nav_Open_Intent(this, MyCart.class);
                break;

/*
            case R.id.nav_settings:
                intentHelper.Nav_Open_Intent(this, Settings.class);
                break;

            case R.id.nav_preferred_landmarks:
                intentHelper.Nav_Open_Intent(this, PreferredLandmarks.class);

                break;

            case R.id.nav_map:
                intentHelper.Nav_Open_Intent(this, ViewMap.class);
                break;

            case R.id.nav_search:
                intentHelper.Nav_Open_Intent(this, SearchLandmark.class);
                break;

            case R.id.nav_saved_landmarks:
                intentHelper.Nav_Open_Intent(this, Saved_Favorite_Landmarks.class);
                break;   */
        }
        return true;
    }
}