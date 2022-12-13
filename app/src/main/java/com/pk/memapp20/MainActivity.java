package com.pk.memapp20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasena;
    private MediaPlayer passoremail;
    private MediaPlayer email;
    private MediaPlayer pass;
    private MediaPlayer both;
    private MediaPlayer welcome;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    boolean isLoged;

    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;
    ConstraintLayout mMainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings=getSharedPreferences("MODE", Context.MODE_PRIVATE);
        isLoged =settings.getBoolean("loged",false);
        mMainLayout=findViewById(R.id.main_layout);

        if (isLoged){
            //Intent i=new Intent(this,HomeActivity.class);
           // startActivity(i);
        }



        mAuth=FirebaseAuth.getInstance();
        correo=findViewById(R.id.correom);
        contrasena=findViewById(R.id.passm);
        passoremail = MediaPlayer.create(this,R.raw.passoremail);
        email = MediaPlayer.create(this,R.raw.email);
        pass = MediaPlayer.create(this,R.raw.password);
        both=MediaPlayer.create(this,R.raw.emptyboth);
        welcome=MediaPlayer.create(this,R.raw.welcome);

        BiometricManager biometricManager=BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()){
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(getApplicationContext(), "Device without fingerprint", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(getApplicationContext(), "Fingerprint working", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(getApplicationContext(), "No fingerprint assigned", Toast.LENGTH_SHORT).show();
        }

        Executor executor= ContextCompat.getMainExecutor(this);
        biometricPrompt=new BiometricPrompt(MainActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
                welcome.start();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        promptInfo=new BiometricPrompt.PromptInfo.Builder().setTitle("MemApp")
                .setDescription("Use FingerPrint to login").setDeviceCredentialAllowed(true).build();
        biometricPrompt.authenticate(promptInfo);

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
                                editor= settings.edit();
                                editor.putBoolean("loged",true);
                                editor.putBoolean("first",true);
                                editor.apply();
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