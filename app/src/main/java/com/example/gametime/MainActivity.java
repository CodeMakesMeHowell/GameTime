package com.example.gametime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://gametime-4360d-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        Button loginBtn = findViewById(R.id.loginbtn);

        //The SignUp text can be clicked
        TextView signupTxt = findViewById(R.id.SignUptxt);

        signupTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Login user
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();

                //checks if username is empty
                if (TextUtils.isEmpty(usernameTxt)){
                    Toast.makeText(MainActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
                    username.setError("Username required");
                    username.requestFocus();
                } else if (TextUtils.isEmpty(passwordTxt)) //checks if password is empty
                {
                    Toast.makeText(MainActivity.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                    password.setError("Password required");
                    password.requestFocus();
                }
                else
                {
                    //search for user info
                    ref.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        //located user with the use of username
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("customer").hasChild(usernameTxt)) //checks if username matches customer
                            {
                                String getPassword = dataSnapshot.child("customer").child(usernameTxt).child("password").getValue(String.class);

                                if(getPassword.equals(passwordTxt)){
                                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                } else  {
                                    Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }

                            } else if(dataSnapshot.child("admin").hasChild(usernameTxt)) { //checks if username matches admin
                                String getPassword = dataSnapshot.child("admin").child(usernameTxt).child("password").getValue(String.class);

                                if(getPassword.equals(passwordTxt)){
                                    Toast.makeText(MainActivity.this, "Logged in as Admin", Toast.LENGTH_SHORT).show();
                                } else  {
                                    Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            } else  { //no username found
                                Toast.makeText(MainActivity.this, "There is no account with this username", Toast.LENGTH_SHORT).show();
                              }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
}