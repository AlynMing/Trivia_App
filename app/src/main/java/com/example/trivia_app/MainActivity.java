package com.example.trivia_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question1)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(0).getAnswer());
        }

        findViewById(R.id.flashcard_question1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something here
                findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_question1).setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.flashcard_answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something here
                findViewById(R.id.flashcard_question1).setVisibility(View.VISIBLE);
                findViewById(R.id.flashcard_answer1).setVisibility(View.INVISIBLE);

            }
        });

        findViewById(R.id.addNewCardButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addCardActivity.class);
                MainActivity.this.startActivityForResult(intent,100);
            }
        });


        findViewById(R.id.nextCardButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCardDisplayedIndex++;

                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                ((TextView) findViewById(R.id.flashcard_question1)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
            }
        });

        findViewById(R.id.deleteCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question1)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();

            }
        });

    }
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;

    int currentCardDisplayedIndex = 0;

    @Override
    protected  void onActivityResult(int requestcode, int resultcode, Intent data){
        if (requestcode == 100 ) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String string1 = data.getExtras().getString("newCardQuestion"); // 'string1' needs to match the key we used when we put the string in the Intent
            String string2 = data.getExtras().getString("newAnswerQuestion");
            Log.d(string1, "onActivityResult() returned: " );
            ((TextView) findViewById(R.id.flashcard_question1)).setText(string1);
            ((TextView) findViewById(R.id.flashcard_answer1)).setText(string2);
            flashcardDatabase.insertCard(new Flashcard(string1, string2));
            allFlashcards = flashcardDatabase.getAllCards();

        }
    }

}