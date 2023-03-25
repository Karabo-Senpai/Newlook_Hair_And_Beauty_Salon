package com.example.newlook_hair_and_beauty_salon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newlook_hair_and_beauty_salon.Users.UserLogin;

public class MainActivity extends AppCompatActivity {

    /*
     GLOBAL VARIABLE DECLARATIONS
     */

    //Creating Variables For XML Hooks
    ImageView imageView;
    TextView appName, devName;

    //Animations Variable Declarations
    Animation sideAnimation, bottomAnimation;

    //Variable To Hold Splash Screen Duration
    private static int SPLASH_SCREEN = 3700;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hiding Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Creating XML Hooks From Declared Variables
        imageView = findViewById(R.id.app_logo);
        appName = findViewById(R.id.appname);
        devName = findViewById(R.id.dev_name);

        //Assigning Animation Hooks
        sideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Setting Animations To Splash Screen Elements
        imageView.setAnimation(sideAnimation);
        appName.setAnimation(bottomAnimation);
        devName.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Creating Intent To Move To The User Log In Activity
                Intent intent = new Intent(getApplicationContext(), UserLogin.class);

                //Creating Pair For Shared Animation Preferences (Animations Will Be Carried Over To The Next Screen)
                Pair[] pair = new Pair[3];

                //Assigning Transition Values To Pairs For Shared Preference
                pair[0] = new Pair<View, String>(imageView, "logo");
                pair[1] = new Pair<View, String>(appName, "logo_text");
                pair[2] = new Pair<View, String>(devName, "dev_text");

                //Syncing Or Binding Animations To Sign In Screen
                ActivityOptions activityOptions =  ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pair);

                //Passing  Animations To Intent
                startActivity(intent,activityOptions.toBundle());
                finish();


            }
        }, SPLASH_SCREEN);


    }
}