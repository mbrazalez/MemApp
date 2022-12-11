package com.pk.memapp20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MapsMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_menu);
    }


    public void irSeeLocation(View view){
        Intent i=new Intent(this, MapsActivity.class);
        startActivity(i);
    }

    public void irHome(View view){
        Intent i=new Intent(this, HomeActivity.class);
        startActivity(i);
    }

    public void irRoute(View view){
        Intent i=new Intent(this, RouteActivity.class);
        startActivity(i);
    }

}