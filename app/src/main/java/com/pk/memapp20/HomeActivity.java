package com.pk.memapp20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity{
    private MediaPlayer logout;
    private FirebaseAuth mAuth;
    Switch swDark;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean nightMode;
    ImageView train;
    ImageView calendar;
    ImageView daily;
    ImageView people;
    ImageView location;
    ImageView help;
    ImageView popupButton;
    ImageView header;
    ImageView logoutb;
    ImageView support;
    Dialog mDialog;
    boolean isLoged;
    boolean firstTime;



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
        nightMode =sharedPreferences.getBoolean("night",false);
        isLoged =sharedPreferences.getBoolean("loged",false);
        firstTime=sharedPreferences.getBoolean("first",true);

        if (nightMode){
            swDark.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }


        swDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor= sharedPreferences.edit();
                    editor.putBoolean("night",false);

                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor= sharedPreferences.edit();
                    editor.putBoolean("night",true);
                }
                editor.apply();

            }
        });



        if (firstTime){
            AlertDialog.Builder alerta=new AlertDialog.Builder(HomeActivity.this);
            alerta.setMessage("Do you want to see a tutorial")
                    .setCancelable(true)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            editor= sharedPreferences.edit();
                            editor.putBoolean("first",false);
                            editor.apply();
                            finish();
                            Intent k=new Intent(HomeActivity.this, TutorialActivity.class);
                            startActivity(k);

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.cancel();
                            editor= sharedPreferences.edit();
                            editor.putBoolean("first",false);
                            editor.apply();
                        }
                    });

            AlertDialog titulo= alerta.create();
            titulo.setTitle("Go to tutorial");
            titulo.show();
        }



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

        support=findViewById(R.id.support);
        MediaPlayer help_mp3=MediaPlayer.create(this,R.raw.help_mp3);
        support.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                help_mp3.start();
                return true;
            }
        });

        logoutb=findViewById(R.id.btn_logout);
        MediaPlayer logout_audio=MediaPlayer.create(this,R.raw.logout2);
        logoutb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                logout_audio.start();
                return true;
            }
        });

        MediaPlayer dark_audio=MediaPlayer.create(this,R.raw.dark_mode);
        swDark.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dark_audio.start();
                return true;
            }
        });

        if(swDark.isChecked()){
            train.setImageResource(R.drawable.traindark);
            calendar.setImageResource(R.drawable.calendardark);
            daily.setImageResource(R.drawable.dailydark);
            location.setImageResource(R.drawable.locationdark);
            help.setImageResource(R.drawable.helpdark);
            people.setImageResource(R.drawable.peopledark);
            header.setImageResource(R.drawable.headerdark);
            logoutb.setImageResource(R.drawable.logoutdark);
        }



    }

    public void viewPopUp(View view){
        editor= sharedPreferences.edit();
        editor.putBoolean("first",false);
        editor.apply();
        popupButton=findViewById(R.id.support);
        mDialog=new Dialog(this);
        mDialog.setContentView(R.layout.popup);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();

    }

    public void irMain(View view){
        editor= sharedPreferences.edit();
        editor.putBoolean("loged",false);
        editor.apply();
        mAuth=FirebaseAuth.getInstance();
        logout.start();
        mAuth.signOut();

        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void irDaily(View view){
        editor= sharedPreferences.edit();
        editor.putBoolean("first",false);
        editor.apply();

        Intent i=new Intent(this, DailyActivity.class);
        startActivity(i);
    }


    public void irTuto(View view){
        editor= sharedPreferences.edit();
        editor.putBoolean("first",false);
        editor.apply();

        Intent i=new Intent(HomeActivity.this, TutorialActivity.class);
        startActivity(i);
    }

    public void irBack(View view){
        editor= sharedPreferences.edit();
        editor.putBoolean("first",false);
        editor.apply();

        Intent i=new Intent(HomeActivity.this, HomeActivity.class);
        startActivity(i);
    }

    public void irCalendar(View view){
        editor= sharedPreferences.edit();
        editor.putBoolean("first",false);
        editor.apply();

        Intent i=new Intent(this, todo_apu.class);
        startActivity(i);
    }

    public void irMaps(View view){
        editor= sharedPreferences.edit();
        editor.putBoolean("first",false);
        editor.apply();

        Intent i=new Intent(this, MapsMenuActivity.class);
        startActivity(i);
    }



}