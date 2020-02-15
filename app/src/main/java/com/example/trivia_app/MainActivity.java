package com.example.trivia_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.flashcard_question1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something here
                findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_question1).setVisibility(View.INVISIBLE);

            }
        });
    }
}
