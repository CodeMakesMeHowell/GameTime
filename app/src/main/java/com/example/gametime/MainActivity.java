package com.example.gametime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gametime.firebase.FirebaseConfig;
import com.example.gametime.firebase.FirebaseDBPaths;
import com.example.gametime.model.User;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected static String usernameTxt;
    protected static String nameTxt;

    //    DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://gametime-4360d-default-rtdb.firebaseio.com/");
    DatabaseReference ref = FirebaseConfig.getInstance().getDbInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);

        Button loginBtn = findViewById(R.id.loginbtn);

        //The SignUp text can be clicked
        TextView signupTxt = findViewById(R.id.SignUptxt);

        signupTxt.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        //Login user
        loginBtn.setOnClickListener(v -> {
            usernameTxt = username.getText().toString();
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
                ref.child(FirebaseDBPaths.USERS.getPath()).addListenerForSingleValueEvent(new ValueEventListener() {
                    //located user with the use of username
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(usernameTxt)) //checks if username matches customer
                        {
                            String getPassword = dataSnapshot.child(usernameTxt).child("password").getValue(String.class);
                            nameTxt = dataSnapshot.child(usernameTxt).child("name").getValue(String.class);

                            if(getPassword != null){
                                if(getPassword.equals(passwordTxt)){
                                    DataSnapshot userSnap = dataSnapshot.child(usernameTxt);
                                    User.currentUser = User.userFromSnapshot(userSnap);
                                    User.currentUser.setUsername(usernameTxt);
                                    if(User.currentUser.isAdmin())
                                    {
                                        Toast.makeText(MainActivity.this, "Logged in as Admin", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    }
                                    Intent i = new Intent(MainActivity.this, SelectionActivity.class);
                                    startActivity(i);
                                } else  {
                                    Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
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
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        moveTaskToBack(true);
    }
}