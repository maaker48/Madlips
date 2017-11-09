package com.example.stephan.madlipsstephandegraaf_pset01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class madlips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madlips);
    }

    public void goToSecond(View view) {
        Intent intent = new Intent(this, madlips_input.class);
        startActivity(intent);
    }
}
