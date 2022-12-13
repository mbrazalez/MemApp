package com.pk.memapp20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TrainBrainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainbrain);
    }

    public void irCorrectGame(View view){
        Intent i=new Intent(this, CorrectGameActivity.class);
        startActivity(i);
    }

    public void irHome(View view){
        Intent i=new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}