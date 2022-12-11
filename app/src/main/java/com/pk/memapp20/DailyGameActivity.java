package com.pk.memapp20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class DailyGameActivity extends AppCompatActivity {
    Random rand;
    int chosen;
    ImageView cepillo;
    ImageView reloj;
    ImageView gafas;
    ImageView ventilador;
    ImageView llave;
    ImageView paraguas;
    TextView txt;
    Vibrator vibrator;
    AppCompatButton back;
    AppCompatButton again;
    TextView title;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailygame);
        vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        MediaPlayer correct=MediaPlayer.create(this,R.raw.correct);
        MediaPlayer wrong=MediaPlayer.create(this,R.raw.wrong);

        txt=findViewById(R.id.text);
        time=0;
        rand=new Random();
        chosen=rand.nextInt(6)+1;
        //1
        cepillo=findViewById(R.id.cepillo);
        //2
        reloj=findViewById(R.id.imageView11);
        //3
        gafas=findViewById(R.id.gafas);
        //4
        ventilador=findViewById(R.id.ventilador);
        //5
        llave=findViewById(R.id.llave);
        //6
        paraguas=findViewById(R.id.paraguas);


        switch (chosen){
            case 1:
                break;
            case 2:
                txt.setText("What object is used to check the hour?");
                break;
            case 3:
                txt.setText("What object is used to see better");
                break;
            case 4:
                txt.setText("What object is used if it's so hot");
                break;
            case 5:
                txt.setText("What object is used to open doors?");
                break;
            case 6:
                txt.setText("What object is used in rainy days?");
                break;
            default:
                break;
        }

        reloj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosen==2 && time==0){
                    time++;
                    correct.start();
                    vibrator.vibrate(1000);
                    Toast.makeText(getApplicationContext(), "The answer is correct!", Toast.LENGTH_SHORT).show();

                }else{
                    if(time==0){
                        wrong.start();
                        vibrator.vibrate(1000);
                    }

                }
            }
        });

        gafas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosen==3 && time==0){
                    time++;
                    correct.start();
                    vibrator.vibrate(1000);
                    Toast.makeText(getApplicationContext(), "The answer is correct!", Toast.LENGTH_SHORT).show();

                }else{
                    if(time==0){
                        wrong.start();
                        vibrator.vibrate(1000);
                    }
                }
            }
        });

        ventilador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosen==4 && time==0){
                    time++;
                    correct.start();
                    vibrator.vibrate(1000);
                    Toast.makeText(getApplicationContext(), "The answer is correct!", Toast.LENGTH_SHORT).show();

                }else{
                    if(time==0){
                        wrong.start();
                        vibrator.vibrate(1000);
                    }
                }
            }
        });

        llave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosen==5 && time==0){
                    time++;
                    correct.start();
                    vibrator.vibrate(1000);
                    Toast.makeText(getApplicationContext(), "The answer is correct!", Toast.LENGTH_SHORT).show();

                }else{
                    if(time==0){
                        wrong.start();
                        vibrator.vibrate(1000);
                    }

                }
            }
        });

        paraguas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosen==6 && time==0){
                    time++;
                    correct.start();
                    vibrator.vibrate(1000);
                    Toast.makeText(getApplicationContext(), "The answer is correct!", Toast.LENGTH_SHORT).show();

                }else{
                    if(time==0){
                        wrong.start();
                        vibrator.vibrate(1000);
                    }
                }
            }
        });

        cepillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosen==1){
                    correct.start();
                    vibrator.vibrate(1000);
                    Toast.makeText(getApplicationContext(), "The answer is correct!", Toast.LENGTH_SHORT).show();

                }else{
                    if(time==0){
                        wrong.start();
                        vibrator.vibrate(1000);
                    }
                }

            }
        });

        back=findViewById(R.id.button);
        MediaPlayer back_audio=MediaPlayer.create(this,R.raw.back);
        back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                back_audio.start();
                return true;
            }
        });

        again=findViewById(R.id.again);
        MediaPlayer again_audio=MediaPlayer.create(this,R.raw.playagain);
        again.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                again_audio.start();
                return true;
            }
        });

        title=findViewById(R.id.id2);
        MediaPlayer title_audio=MediaPlayer.create(this,R.raw.daily_objects);
        again.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                title_audio.start();
                return true;
            }
        });


    }



    public void irDaily(View view){
        Intent i=new Intent(this, DailyActivity.class);
        startActivity(i);
    }

    public void irDailyGame(View view){
        Intent i=new Intent(this, DailyGameActivity.class);
        startActivity(i);
    }
}