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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;
import java.util.List;


public class ToDoListActivity extends AppCompatActivity {
    List<String> toDoList;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    EditText editText;
    Vibrator vibrator;
    AppCompatButton back;
    AppCompatButton add;
    ImageView mic;
    TextView title;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    List<String> crossed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings=getSharedPreferences("MODE", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_todo);
        vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        toDoList=new ArrayList<>();
        crossed=new ArrayList<>();
        arrayAdapter=new ArrayAdapter<>(this,R.layout.list_view_layout,toDoList);
        listView=findViewById(R.id.id_list_view);
        listView.setAdapter(arrayAdapter);
        editText=findViewById(R.id.id_edit_text);

        String wordString=settings.getString("words","");
        String[] items=wordString.split(",");

        for (int i=0; i<items.length;i++){
            toDoList.add(items[i]);
        }
        arrayAdapter.notifyDataSetChanged();


        back=findViewById(R.id.button);
        MediaPlayer back_audio=MediaPlayer.create(this,R.raw.back);
        back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                back_audio.start();
                return true;
            }
        });

        add=findViewById(R.id.add);
        MediaPlayer add_audio=MediaPlayer.create(this,R.raw.add);
        add.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                add_audio.start();
                return true;
            }
        });

        mic=findViewById(R.id.mic_in_todo);
        MediaPlayer mic_audio=MediaPlayer.create(this,R.raw.mic_audio);
        mic.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mic_audio.start();
                return true;
            }
        });

        title=findViewById(R.id.id2);
        MediaPlayer title_audio=MediaPlayer.create(this,R.raw.todo_audio);
        title.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                title_audio.start();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView=(TextView) view;

                StringBuilder stringBuilder = new StringBuilder();
                settings.edit().remove("words");

                if (!crossed.contains(toDoList.get(i))) {
                    crossed.add(toDoList.get(i));
                    textView.setPaintFlags(textView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG) ;
                    vibrator.vibrate(1000);

                    for (int j = 0; j < toDoList.size(); j++) {
                        if (!crossed.contains(toDoList.get(j))){
                            stringBuilder.append(toDoList.get(j));
                            stringBuilder.append(",");
                        }
                    }
                    settings=getSharedPreferences("MODE", Context.MODE_PRIVATE);
                    editor = settings.edit();
                    editor.putString("words", stringBuilder.toString());
                    editor.commit();

                }else{
                    crossed.remove(toDoList.get(i));
                    textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    vibrator.vibrate(1000);
                    for (int k=0; k<toDoList.size();k++){
                        if (!crossed.contains(toDoList.get(k))){
                            stringBuilder.append(toDoList.get(k));
                            stringBuilder.append(",");
                        }
                    }
                    settings=getSharedPreferences("MODE", Context.MODE_PRIVATE);
                    editor = settings.edit();
                    editor.putString("words",stringBuilder.toString());
                    editor.commit();


                }
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
        if(!editText.getText().toString().equals("")) {
            toDoList.add(editText.getText().toString());
            arrayAdapter.notifyDataSetChanged();

        }else {
            Toast.makeText(getApplicationContext(), "Introduce a task!", Toast.LENGTH_SHORT).show();

        }

        editText.setText("");
        settings.edit().remove("words");

        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<toDoList.size();i++){
            stringBuilder.append(toDoList.get(i));
            stringBuilder.append(",");
        }
        settings=getSharedPreferences("MODE", Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString("words",stringBuilder.toString());
        editor.commit();

    }

    public void irDaily(View view){
        Intent i=new Intent(this, DailyActivity.class);
        startActivity(i);
    }
}