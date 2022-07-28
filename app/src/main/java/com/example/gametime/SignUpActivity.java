package com.example.gametime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gametime.firebase.FirebaseDBPaths;
import com.example.gametime.model.Event;
import com.example.gametime.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SignUpActivity extends AppCompatActivity {
    private EditText editTextRegisterFullName, editTextRegisterUsername, editTextRegisterPassword;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://gametime-4360d-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView signInTxt = findViewById(R.id.SignIntxt);

        signInTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignIn = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intentSignIn);
                finish();
            }
        });

        editTextRegisterFullName = findViewById(R.id.name);
        editTextRegisterUsername = findViewById(R.id.username);
        editTextRegisterPassword = findViewById(R.id.password);

        final Button buttonRegister = findViewById(R.id.registerBtn);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullNameTxt = editTextRegisterFullName.getText().toString();
                String usernameTxt = editTextRegisterUsername.getText().toString();
                String passwordTxt = editTextRegisterPassword.getText().toString();

                if (TextUtils.isEmpty(fullNameTxt)) {
                    Toast.makeText(SignUpActivity.this, "Please enter your full name", Toast.LENGTH_SHORT).show();
                    editTextRegisterFullName.setError("Full name required");
                    editTextRegisterFullName.requestFocus();
                } else if (TextUtils.isEmpty(usernameTxt)) {
                    Toast.makeText(SignUpActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
                    editTextRegisterUsername.setError("Username is required");
                    editTextRegisterUsername.requestFocus();
                } else if (TextUtils.isEmpty(passwordTxt)) {
                    Toast.makeText(SignUpActivity.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                    editTextRegisterPassword.setError("Password is required");
                    editTextRegisterPassword.requestFocus();
                } else  {
                        ref.child(FirebaseDBPaths.USERS.getPath()).child(FirebaseDBPaths.CUSTOMERS.getPath()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(usernameTxt))
                            {
                                Toast.makeText(SignUpActivity.this, "Username is already taken. Please enter another username.", Toast.LENGTH_LONG).show();
                            } else  {
                                User newUser = new User(fullNameTxt, usernameTxt, passwordTxt, false);
                                newUser.addToDb();

                                Toast.makeText(SignUpActivity.this, "Registered successfully!", Toast.LENGTH_LONG).show();
                                Intent intentSignIn = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intentSignIn);
                                finish();
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