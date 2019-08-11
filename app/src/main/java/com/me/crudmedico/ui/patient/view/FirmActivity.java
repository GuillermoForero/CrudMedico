package com.me.crudmedico.ui.patient.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.me.crudmedico.R;

public class FirmActivity extends AppCompatActivity {
    SignaturePad signaturePad;
    Button saveButton, clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm);

        signaturePad = findViewById(R.id.signaturePad);
        saveButton = findViewById(R.id.saveButton);
        clearButton = findViewById(R.id.clearButton);

        //disable both buttons at start
        saveButton.setEnabled(false);
        clearButton.setEnabled(false);

        //change screen orientation to landscape mode
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                saveButton.setEnabled(true);
                clearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                saveButton.setEnabled(false);
                clearButton.setEnabled(false);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //write code for saving the signature here
                Intent data = new Intent();
                data.setData(Uri.parse(signaturePad.getSignatureSvg()));
                setResult(RESULT_OK, data);
//---close the activity---
                finish();
                Toast.makeText(FirmActivity.this, "Signature Saved", Toast.LENGTH_SHORT).show();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
            }
        });
    }
}
