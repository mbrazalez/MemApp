package com.pk.memapp20;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class ScannersActivity extends AppCompatActivity {
    AppCompatButton btn_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanners);
        btn_qr=findViewById(R.id.qr);

        btn_qr.setOnClickListener(view ->
                {
                    scanCode();
                });
    }

    private void scanCode(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash o");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher=registerForActivityResult(new ScanContract(),
            result -> {
                if (result.getContents() != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScannersActivity.this);
                    builder.setMessage(result.getContents())
                            .setCancelable(true)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();

                                }
                            })
                            .setNegativeButton("COPY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                    ClipData clip = ClipData.newPlainText("pelao", result.getContents());
                                    clipboard.setPrimaryClip(clip);
                                }
                            });

                    AlertDialog titulo = builder.create();
                    titulo.setTitle("Result");
                    titulo.show();
                }
            });

    public void irDaily(View view){
        Intent i=new Intent(this, DailyActivity.class);
        startActivity(i);
    }

    public void irNFC(View view){
        Intent i=new Intent(this, NfcActivity.class);
        startActivity(i);
    }

}