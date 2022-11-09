package com.pk.memapp20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity{
    private MediaPlayer logout;
    private FirebaseAuth mAuth;
    Switch swDark;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean nigthMode;
    ImageView train;
    ImageView calendar;
    ImageView daily;
    ImageView people;
    ImageView location;
    ImageView help;
    ImageView popupButton;
    ImageView header;
    ImageView logoutb;
    Dialog mDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.Theme_Dark);
        }else{
            setTheme(R.style.Theme_Light);
        }
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logout=MediaPlayer.create(this,R.raw.logout);
        mAuth=FirebaseAuth.getInstance();
        swDark=findViewById(R.id.mode);
        logoutb=findViewById(R.id.btn_logout);
        header=findViewById(R.id.imageView2);
        sharedPreferences=getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nigthMode=sharedPreferences.getBoolean("night",false);


        swDark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    train.setImageResource(R.drawable.traindark);
                    calendar.setImageResource(R.drawable.calendardark);
                    daily.setImageResource(R.drawable.dailydark);
                    location.setImageResource(R.drawable.locationdark);
                    help.setImageResource(R.drawable.helpdark);
                    people.setImageResource(R.drawable.peopledark);
                    header.setImageResource(R.drawable.headerdark);
                    logoutb.setImageResource(R.drawable.logoutdark);


                    editor= sharedPreferences.edit();
                    editor.putBoolean("night",true);

                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor= sharedPreferences.edit();
                    editor.putBoolean("night",true);
                }
                editor.apply();



            }
        });


        popupButton=findViewById(R.id.support);
        mDialog=new Dialog(this);

        train=findViewById(R.id.train);
        MediaPlayer train_audio=MediaPlayer.create(this,R.raw.traina);
        train.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                train_audio.start();
                return true;
            }
        });

        calendar=findViewById(R.id.calendar);
        MediaPlayer calendar_audio=MediaPlayer.create(this,R.raw.calendar);
        calendar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                calendar_audio.start();
                return true;
            }
        });

        daily=findViewById(R.id.daily);
        MediaPlayer daily_audio=MediaPlayer.create(this,R.raw.daily);
        daily.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                daily_audio.start();
                return true;
            }
        });

        people=findViewById(R.id.people);
        MediaPlayer people_audio=MediaPlayer.create(this,R.raw.people);
        people.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                people_audio.start();
                return true;
            }
        });

        location=findViewById(R.id.location);
        MediaPlayer location_audio=MediaPlayer.create(this,R.raw.location);
        location.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                location_audio.start();
                return false;
            }
        });

        help=findViewById(R.id.help);
        MediaPlayer help_audio=MediaPlayer.create(this,R.raw.help);
        help.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                help_audio.start();
                return true;
            }
        });


    }

    public void viewPopUp(View view){
        popupButton=findViewById(R.id.support);
        mDialog=new Dialog(this);
        mDialog.setContentView(R.layout.popup);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();

    }

    public void irMain(View view){
        mAuth=FirebaseAuth.getInstance();
        logout.start();
        mAuth.signOut();
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void irDaily(View view){
        Intent i=new Intent(this, DailyActivity.class);
        startActivity(i);
    }
}