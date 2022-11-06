package com.pk.memapp20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth=FirebaseAuth.getInstance();

    }

    public void irMain(View view){
        mAuth=FirebaseAuth.getInstance();
        mAuth.signOut();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void irDaily(View view){
        Intent i=new Intent(this, DailyActivity.class);
        startActivity(i);
    }
}