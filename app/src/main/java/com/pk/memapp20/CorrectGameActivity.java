package com.pk.memapp20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

public class CorrectGameActivity extends AppCompatActivity {
    ImageView arbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_game);
        arbol=findViewById(R.id.arbol);

    }

    float x,y;
    float dx,dy;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            x=event.getX();
            y=event.getY();
        }

        if(event.getAction()==MotionEvent.ACTION_MOVE){
            dx=event.getX()-x;
            dy=event.getY()-y;

            arbol.setX(arbol.getX()+dx);
            arbol.setY(arbol.getY()+dy);

            x=event.getX();
            y=event.getY();
        }
        return super.onTouchEvent(event);
    }
}


