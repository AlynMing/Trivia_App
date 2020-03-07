package com.example.trivia_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

        findViewById(R.id.addNewCardButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addCardActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected  void onActivityResult(int requestcode, int resultcode, Intent data){
        if (requestcode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String string1 = data.getExtras().getString("newCardQuestion"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = data.getExtras().getString("newAnswerQuestion");
            //Log.d(string1, "onActivityResult() returned: " );
            ((TextView) findViewById(R.id.flashcard_question1)).setText(string1);
            ((TextView) findViewById(R.id.flashcard_answer1)).setText(string2);
        }
    }
}