package com.example.newlook_hair_and_beauty_salon.Users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newlook_hair_and_beauty_salon.Admin.AdminDashboard;
import com.example.newlook_hair_and_beauty_salon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class UserLogin extends AppCompatActivity {

      /*
     GLOBAL VARIABLE DECLARATIONS
     */

    //Initialising Variables For XML Components
    ImageView imageView;
    TextView logoname;
    TextInputLayout email, password;

    //Creating Variable For Register Button For Some Action To Be Performed When Pressed
    Button register;

    //Variable For LogIn Button
    Button login, forgot_pass;

    //Setting Up Firebase Authentication
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    //Admin LogIn Credentials
    public static final String adminEmail = "bonganiadmin@admin.com";
    public static final String adminPassword = "Admin@123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        //Initialising Declared Firebase Authentication Variables
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        //Assigning Respective XML Components To Declared Variables
        register = findViewById(R.id.reg);
        imageView = findViewById(R.id.logo_img);
        logoname = findViewById(R.id.logo_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.logBtn);
        forgot_pass = findViewById(R.id.forgot);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Creating Intent To Move To Login Screen If A User Is Not Registered With Us
                Intent i = new Intent(UserLogin.this, UserSignUp.class);
                startActivity(i);
            }
        });

        //Creating On Click Listener For When The Login / Sign Up Button Is Pressed
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateEmail() | !validatePassword()) {

                    return;
                } else {

                    String inputEmail = email.getEditText().getText().toString().trim();
                    String inputPassword = password.getEditText().getText().toString().trim();

                    mAuth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            //Checking If User That's Logged In Is The Admin
                            if (inputEmail.equals(adminEmail) && inputPassword.equals(adminPassword)) {
                                Intent intent = new Intent(getApplicationContext(), AdminDashboard.class);
                                startActivity(intent);
                                finish();

                            }
                            //Condition For When Sign In Is Successful
                            else if (task.isSuccessful()) {

                                Toast.makeText(UserLogin.this, " " + mAuth.getCurrentUser().getEmail() + " " + "Has Successfully Logged In ", Toast.LENGTH_SHORT).show();
                                //Moving To The Dashboard Screen
                                Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                                startActivity(intent);
                                finish();


                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            //Toast To Show User Why The Log In Failed
                            Toast.makeText(UserLogin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    /*
      Input Validation Methods
     */
    private Boolean validateEmail() {

        String valName = email.getEditText().getText().toString();

        if (valName.isEmpty()) {

            email.setError("Email cannot Be Left Empty!");
            return false;
        }

        //
        else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    //Validating User Password
    private Boolean validatePassword() {

        //Variable To Check Validations
        String valName = password.getEditText().getText().toString();

        //Checking If Password Field Is Empty Or Not
        if (valName.isEmpty()) {

            password.setError("Password cannot Be Left Empty!");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }


}