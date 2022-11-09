package com.pk.memapp20;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ToDoListActivity extends AppCompatActivity {
    List<String> toDoList;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    EditText editText;
    Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        toDoList=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<>(this,R.layout.list_view_layout,toDoList);
        listView=findViewById(R.id.id_list_view);
        listView.setAdapter(arrayAdapter);
        editText=findViewById(R.id.id_edit_text);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView=(TextView) view;
                textView.setPaintFlags(textView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG) ;
                vibrator.vibrate(1000);
            }
        });
    }

    private static final int RECOGNISE_SPEECH_ACTIVITY=1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case RECOGNISE_SPEECH_ACTIVITY:
                if (resultCode==RESULT_OK && null != data) {
                    ArrayList<String> speech = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String strSpeech2Text = speech.get(0);
                    editText.setText(strSpeech2Text);
                }
                break;
            default:
                break;


        }
    }
    public void Hablar(View view){
        Intent intentActionRecognizeSpeech=new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intentActionRecognizeSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-UK");
        try{
            startActivityForResult(intentActionRecognizeSpeech,
                    RECOGNISE_SPEECH_ACTIVITY);
        } catch (ActivityNotFoundException a){
            Toast.makeText(getApplicationContext(),"Voice recognition failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void addItemToList(View view){
        toDoList.add(editText.getText().toString());
        arrayAdapter.notifyDataSetChanged();
        editText.setText("");

    }

    public void irDaily(View view){
        Intent i=new Intent(this, DailyActivity.class);
        startActivity(i);
    }
}