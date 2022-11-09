package com.pk.memapp20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DailyActivity extends AppCompatActivity {
    AppCompatButton everyday;
    AppCompatButton to_do;
    AppCompatButton nfc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);


        everyday=findViewById(R.id.every);
        MediaPlayer everyday_audio=MediaPlayer.create(this,R.raw.everyday);
        everyday.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                everyday_audio.start();
                return true;
            }
        });

        to_do=findViewById(R.id.to_do);
        MediaPlayer todo_audio=MediaPlayer.create(this,R.raw.to_do);
        everyday.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                todo_audio.start();
                return true;
            }
        });

        nfc=findViewById(R.id.nfc);
        MediaPlayer nfc_audio=MediaPlayer.create(this,R.raw.nfc);
        nfc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                nfc_audio.start();
                return true;
            }
        });

    }

    public void irToDo(View view){
        Intent i=new Intent(this, ToDoListActivity.class);
        startActivity(i);
    }

    public void irHome(View view){
        Intent i=new Intent(this, HomeActivity.class);
        startActivity(i);
    }
}