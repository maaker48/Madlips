package com.example.stephan.madlipsstephandegraaf_pset01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class madlips_story extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madlips_story);

        Intent intent = getIntent();
        String recivedText = intent.getStringExtra("ourtekst");

        TextView textview = findViewById(R.id.Storytimeoutput);
        textview.setText(recivedText);
    }
}
