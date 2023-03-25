package com.example.newlook_hair_and_beauty_salon.Users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.newlook_hair_and_beauty_salon.Classes.Bookings;
import com.example.newlook_hair_and_beauty_salon.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Book_Appointment extends AppCompatActivity {

    /*
      GLOBAL VARIABLE DECLARATIONS
     */

    String selected_method;
    //Declaring Firebase Variables
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    Bookings bookings;
    Button book;
    private DatePickerDialog picker;
    EditText name, sname, contact, service, message, booking_date;
    RadioGroup confirmation_radio_group;
    RadioButton email, sms, selected_type;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        //Instantiating Firebase Declared Variables
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(mAuth.getCurrentUser().getUid());
        confirmation_radio_group = findViewById(R.id.confirmation);
        email = findViewById(R.id.radio_email);
        sms = findViewById(R.id.radio_sms);
        booking_date = findViewById(R.id.user_date_input);
        name = findViewById(R.id.username_input);
        sname = findViewById(R.id.user_lastname_input);
        contact = findViewById(R.id.user_contact_input);
        service = findViewById(R.id.user_service_input);
        message = findViewById(R.id.user_message_input);
        book = findViewById(R.id.book_btn);
        back = findViewById(R.id.nav_back_icon);


        //Setting Up On Click Listener For Book Date Edit Text Section To Display Calender To Choose Date Instead Of String Date
        booking_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating Calender Class Object
                final Calendar calendar = Calendar.getInstance();

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //Defining  Date Picker To Be Called When Booking Date Edit Text Is Clicked
                picker = new DatePickerDialog(Book_Appointment.this , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        booking_date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                },year,month,day);

                picker.show();
            }

        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                startActivity(intent);
            }
        });


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //
                int selected_item_id = confirmation_radio_group.getCheckedRadioButtonId();

                selected_type = (RadioButton) findViewById(selected_item_id);

                //Getting Data That Was Inputted By The User In The Register Screen Form Before Passing It Into The Database
                String fname = name.getText().toString();
                String lname = sname.getText().toString();
                String contact_no = contact.getText().toString();
                String service_type = service.getText().toString();
                String date = booking_date.getText().toString();
                String extra_message = message.getText().toString();

                if (selected_item_id == -1) {
                    Toast.makeText(Book_Appointment.this, "Confirmation Method Not Selected", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        if (email.isChecked()) {
                            selected_method = "E-Mail";
                        } else {
                            selected_method = "SMS";
                        }

                        bookings = new Bookings(fname, lname, contact_no, service_type, date, selected_method, extra_message);

                        DatabaseReference databaseReference = firebaseDatabase.getReference(mAuth.getCurrentUser().getUid());

                        databaseReference.child("appointments").push().setValue(bookings).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                Toast.makeText(Book_Appointment.this, "Booking Added, Await Confirmation", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), Appointments.class);
                                startActivity(intent);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(Book_Appointment.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (Exception e) {

                        Toast.makeText(Book_Appointment.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}