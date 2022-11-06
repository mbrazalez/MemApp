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

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasena;
    private MediaPlayer passoremail;
    private MediaPlayer email;
    private MediaPlayer pass;
    private MediaPlayer both;
    private MediaPlayer welcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        correo=findViewById(R.id.correom);
        contrasena=findViewById(R.id.passm);
        passoremail = MediaPlayer.create(this,R.raw.passoremail);
        email = MediaPlayer.create(this,R.raw.email);
        pass = MediaPlayer.create(this,R.raw.password);
        both=MediaPlayer.create(this,R.raw.emptyboth);
        welcome=MediaPlayer.create(this,R.raw.welcome);

    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser currentUser= mAuth.getCurrentUser();
        //updateUI(CurrentUser)
    }

    public void iniciarSesion(View view){
        if (!correo.getText().toString().trim().isEmpty() && !contrasena.getText().toString().trim().isEmpty()) {
            mAuth.signInWithEmailAndPassword(correo.getText().toString(), contrasena.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(i);
                                welcome.start();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                                passoremail.start();
                                //updateUI(null);
                            }
                        }
                    });
        }else{
            if(correo.getText().toString().trim().isEmpty() && contrasena.getText().toString().trim().isEmpty()) {
                both.start();
            }else {
                if (correo.getText().toString().trim().isEmpty()) email.start();
                if (contrasena.getText().toString().trim().isEmpty()) pass.start();
            }

        }

    }

    public void irStart(View view){
        Intent i=new Intent(this,CreateActivity.class);
        startActivity(i);

    }
}