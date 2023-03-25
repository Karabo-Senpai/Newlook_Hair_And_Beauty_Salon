package com.example.newlook_hair_and_beauty_salon.Helpers;

import android.content.Context;
import android.content.Intent;

//Class To Help Navigation Menu Move From Screens Smoothly
public class IntentHelper {

    public void Nav_Open_Intent(Context context, Class newActivity) {

        Intent intent = new Intent(context, newActivity);
        intent.putExtra("New Activity", newActivity);
        context.startActivity(intent);


    }

}
