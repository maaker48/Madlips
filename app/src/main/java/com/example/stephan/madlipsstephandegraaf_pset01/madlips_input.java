package com.example.stephan.madlipsstephandegraaf_pset01;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class madlips_input extends AppCompatActivity {
    private Story verhaal;
    String[] verhaaltjes = {"madlib0_simple.txt", "madlib1_tarzan.txt", "madlib2_university.txt",
            "madlib3_clothes.txt", "madlib4_dance.txt"};
    Random rand = new Random();
    private int placeholderRemaining;
    private int placeholderTotal;
    private TextView placeholderProgress;
    private EditText hint;
    private Button gotoNextbutton;
    private Button submitButton;
    private int randomStory = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_madlips_input);

        if(savedInstanceState != null) {
            randomStory = savedInstanceState.getInt("story");
            Log.d("savestate", Integer.toString(randomStory));
            verhaal = (Story)savedInstanceState.getSerializable("storyClass");
        }
        gotoNextbutton = findViewById(R.id.button3);
        gotoNextbutton.setVisibility(View.INVISIBLE);
        initStory();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("savestate", "ssaving story");
        Log.d("savestate", Integer.toString(randomStory));
        savedInstanceState.putInt("story", randomStory);
        savedInstanceState.putSerializable("storyClass", verhaal);
    }

    @Override
    public void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        randomStory = saveInstanceState.getInt("story");
    }

    public void initStory() {
        if (randomStory == -1) {
            randomStory = rand.nextInt(4);
        }

        if(verhaal == null) {
            AssetManager assetInputStream = getAssets();
            InputStream loadFile = null;

            try {
                loadFile = assetInputStream.open(verhaaltjes[randomStory]);
                if (loadFile != null)
                    Log.d("test", "yes");
            } catch (IOException e) {
                e.printStackTrace();
            }

            verhaal = new Story(loadFile);
        }
        placeholderProgress = findViewById(R.id.placeholderProgress);
        hint = findViewById(R.id.input);

        hint.setHint(verhaal.getNextPlaceholder());
        modifyPlaceholderCounter();

    }

    public void modifyPlaceholderCounter () {
        placeholderRemaining = verhaal.getPlaceholderRemainingCount();
        placeholderTotal = verhaal.getPlaceholderCount();
        String placeholder = "(" +  (placeholderTotal - placeholderRemaining) + "/" + placeholderTotal + ")";
        placeholderProgress.setText(placeholder);
    }
    public void goToStory(View view) {
        Intent intent = new Intent(this, madlips_story.class);
        intent.putExtra("ourtekst", verhaal.toString());

        startActivity(intent);
        finish();
    }

    public void submitWord(View view) {
        Button submitButton = findViewById(R.id.button2);
        EditText inputfield = findViewById(R.id.input);
        verhaal.fillInPlaceholder(inputfield.getText().toString());
        hint.setHint(verhaal.getNextPlaceholder());
        modifyPlaceholderCounter();
        inputfield.setText("");
        if (placeholderRemaining == 0) {
            inputfield.setVisibility(view.INVISIBLE);
            placeholderProgress.setText("press the button to go to next page");
            submitButton.setVisibility(view.INVISIBLE);
            gotoNextbutton.setVisibility(view.VISIBLE);
        }
    }
}
