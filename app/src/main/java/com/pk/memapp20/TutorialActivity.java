package com.pk.memapp20;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TutorialActivity extends AppCompatActivity {
    AppCompatButton next;
    AppCompatButton back;
    ImageView imageTuto;
    TextView textTuto;
    AppCompatButton gohome;
    TextView title;

    int counter;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        next=findViewById(R.id.next);
        back=findViewById(R.id.prev);
        imageTuto=findViewById(R.id.tutodark);
        textTuto=findViewById(R.id.texttuto);

        counter=1;

        gohome=findViewById(R.id.gohome);
        MediaPlayer home_audio=MediaPlayer.create(this,R.raw.gohome);
        gohome.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                home_audio.start();
                return true;
            }
        });

        MediaPlayer next_audio=MediaPlayer.create(this,R.raw.next);
        next.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                next_audio.start();
                return true;
            }
        });

        MediaPlayer prev_audio=MediaPlayer.create(this,R.raw.next);
        back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                prev_audio.start();
                return true;
            }
        });

        title=findViewById(R.id.title);
        MediaPlayer title_audio=MediaPlayer.create(this,R.raw.tutorial);
        title.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                title_audio.start();
                return true;
            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter<16)
                    counter++;

                switch (counter){
                    case 1:
                        imageTuto.setImageResource(R.drawable.dark_on);
                        textTuto.setText("Once the dark mode is applied you can pressed again to come back to the light mode");
                        break;
                    case 2:
                        imageTuto.setImageResource(R.drawable.todo_add);
                        textTuto.setText("We also have an to-do list in the daily section in which we can add tasks using the add button");
                        break;
                    case 3:
                        imageTuto.setImageResource(R.drawable.todo_crossed);
                        textTuto.setText("Once we have added a task to the list we can cross it out if it's done by clicking on it");
                        break;
                    case 4:
                        imageTuto.setImageResource(R.drawable.calendar_back_click);
                        textTuto.setText("All buttons in the app are autodescriptive using audio, you can their just just by keeping pressed them");
                        break;
                    case 5:
                        imageTuto.setImageResource(R.drawable.calendar_add_click);
                        textTuto.setText("In the calendar we can add a new event clicking on the plus icon");
                        break;
                    case 6:
                        imageTuto.setImageResource(R.drawable.calendar_add_task);
                        textTuto.setText("Here you can write your event, choose a date and save it in the list using the save button");
                        break;
                    case 7:
                        imageTuto.setImageResource(R.drawable.calendar_add_date);
                        textTuto.setText("Adding date");
                        break;
                    case 8:
                        imageTuto.setImageResource(R.drawable.calendar_add_tasksave);
                        textTuto.setText("Saving it in the list");
                        break;
                    case 9:
                        imageTuto.setImageResource(R.drawable.calendar_add_taskvoice);
                        textTuto.setText("We can also write events using voice, clicking in mic icon");
                        break;
                    case 10:
                        imageTuto.setImageResource(R.drawable.calendar_add_voice);
                        break;
                    case 11:
                        imageTuto.setImageResource(R.drawable.calendar_click_check);
                        textTuto.setText("If the event is completed, we can check it");
                        break;
                    case 12:
                        imageTuto.setImageResource(R.drawable.calendar_checked);
                        textTuto.setText("If the event is completed, we can check it");
                    case 13:
                        imageTuto.setImageResource(R.drawable.calendar_swipeleft_edit);
                        textTuto.setText("We can edit an event swipping it to the left");
                        break;
                    case 14:
                        imageTuto.setImageResource(R.drawable.calendar_swiperoght_remove);
                        textTuto.setText("We can delete an event swipping it to the right");
                        break;
                    case 15:
                        imageTuto.setImageResource(R.drawable.calendar_remove);
                        break;
                    case 16:
                        Toast.makeText(getApplicationContext(), "Congratulations you have finished the tutorial!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter > 0)
                    counter--;
                switch (counter){
                    case 1:
                        imageTuto.setImageResource(R.drawable.dark_off);
                        textTuto.setText("By pressing the switch we can swap the application theme");
                        break;
                    case 2:
                        imageTuto.setImageResource(R.drawable.dark_on);
                        textTuto.setText("Once the dark mode is applied you can pressed again to come back to the light mode");
                        break;
                    case 3:
                        imageTuto.setImageResource(R.drawable.todo_add);
                        textTuto.setText("We also have an to-do list in the daily section in which we can add tasks using the add button");
                        break;
                    case 4:
                        imageTuto.setImageResource(R.drawable.todo_crossed);
                        textTuto.setText("Once we have added a task to the list we can cross it out if it's done by clicking on it");
                        break;
                    case 5:
                        imageTuto.setImageResource(R.drawable.calendar_back_click);
                        textTuto.setText("All buttons in the app are autodescriptive using audio, you can their just just by keeping pressed them");
                        break;
                    case 6:
                        imageTuto.setImageResource(R.drawable.calendar_add_click);
                        textTuto.setText("In the calendar we can add a new event clicking on the plus icon");
                        break;
                    case 7:
                        imageTuto.setImageResource(R.drawable.calendar_add_task);
                        textTuto.setText("Here you can write your event, choose a date and save it in the list using the save button");
                        break;
                    case 8:
                        imageTuto.setImageResource(R.drawable.calendar_add_date);
                        textTuto.setText("Adding date");
                        break;
                    case 9:
                        imageTuto.setImageResource(R.drawable.calendar_add_tasksave);
                        textTuto.setText("Saving it in the list");
                        break;
                    case 10:
                        imageTuto.setImageResource(R.drawable.calendar_add_taskvoice);
                        textTuto.setText("We can also write events using voice, clicking in mic icon");
                        break;
                    case 11:
                        imageTuto.setImageResource(R.drawable.calendar_add_voice);
                        break;
                    case 12:
                        imageTuto.setImageResource(R.drawable.calendar_click_check);
                        textTuto.setText("If the event is completed, we can check it");
                        break;
                    case 13:
                        imageTuto.setImageResource(R.drawable.calendar_checked);
                        textTuto.setText("If the event is completed, we can check it");
                        break;
                    case 14:
                        imageTuto.setImageResource(R.drawable.calendar_swipeleft_edit);
                        textTuto.setText("We can edit an event swipping it to the left");
                        break;
                    case 15:
                        imageTuto.setImageResource(R.drawable.calendar_swiperoght_remove);
                        textTuto.setText("We can delete an event swipping it to the right");
                        break;
                    case 16:
                        imageTuto.setImageResource(R.drawable.calendar_remove);
                        break;
                    default:
                        break;
                }

            }
        });


    }

    public void irHome(View view){
        Intent i=new Intent(this, HomeActivity.class);
        startActivity(i);


    }
}