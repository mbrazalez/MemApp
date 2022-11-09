package com.pk.memapp20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class CreateActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasena;
    private EditText name;
    private EditText confirmPass;
    private MediaPlayer name_audio;
    private MediaPlayer email;
    private MediaPlayer pass;
    private MediaPlayer both;
    private MediaPlayer welcome;
    private MediaPlayer another;
    private MediaPlayer confirm;
    private MediaPlayer confirmfield;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mAuth= FirebaseAuth.getInstance();

        correo=findViewById(R.id.correos);
        contrasena=findViewById(R.id.passs);
        name=findViewById(R.id.name);
        confirmPass=findViewById(R.id.passconfirm);


        name_audio = MediaPlayer.create(this,R.raw.name);
        email = MediaPlayer.create(this,R.raw.email);
        pass = MediaPlayer.create(this,R.raw.password);
        both=MediaPlayer.create(this,R.raw.emptyboth);
        welcome=MediaPlayer.create(this,R.raw.welcome);
        another=MediaPlayer.create(this,R.raw.anotheraccount);
        confirm=MediaPlayer.create(this,R.raw.passdif);
        confirmfield=MediaPlayer.create(this,R.raw.passdif);

    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser currentUser= mAuth.getCurrentUser();
    }


    public void registrarUsuario(View view){
        if (!name.getText().toString().trim().isEmpty() && !correo.getText().toString().trim().isEmpty() && !contrasena.getText().toString().trim().isEmpty() && confirmPass.getText().toString().trim().equals(contrasena.getText().toString().trim())) {
            mAuth.createUserWithEmailAndPassword(correo.getText().toString(), contrasena.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getApplicationContext(), "The account has been created properly.", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(i);
                                welcome.start();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                                another.start();
                                //updateUI(null);
                            }
                        }
                    });
        }else{
            if (!confirmPass.getText().toString().trim().equals(contrasena.getText().toString().trim())){
                confirm.start();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(name.getText().toString().trim().isEmpty()) {
                name_audio.start();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(correo.getText().toString().trim().isEmpty()){
                email.start();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(contrasena.getText().toString().trim().isEmpty()) {
                if(confirmPass.getText().toString().trim().isEmpty()) {
                    pass.start();
                }else if(!confirmPass.getText().toString().trim().isEmpty()){
                    confirmfield.start();

                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    public void irStart(View view){
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }
}