package com.example.trivia_app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private RelativeLayout mRelativeLayout;
    private Button mButton;

    private PopupWindow mPopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

//        String q1 = ((TextView) findViewById(R.id.flashcard_question1)).getText().toString();
//        String a1 = ((TextView) findViewById(R.id.flashcard_answer1)).getText().toString();
//
//        flashcardDatabase.insertCard(new Flashcard(q1,a1));
//        allFlashcards = flashcardDatabase.getAllCards();

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
                if (allFlashcards != null && allFlashcards.size() > 0) {
                    if(allFlashcards.size() == 1){
                        Log.d("State- size 1:-","One card in State. One Flash Cards identified. ");
                        Log.d("Database:-", allFlashcards.toString());

                        // Else show the empty state
                        switch (v.getId()){
                            case R.id.nextCardButton:
                                alertDialogNextCard();
                                break;
                        }
                    }
                    if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                        currentCardDisplayedIndex = 0;
                        ((TextView) findViewById(R.id.flashcard_question1)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                        ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                    }
                    ((TextView) findViewById(R.id.flashcard_question1)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());

                }
                else {
                        if( allFlashcards.size() == 0){
                        Log.d("State1 - size 0:-","No more cards in State. No Flash Cards identified. ");
                        Log.d("Database:-", allFlashcards.toString());
                            switch (v.getId()){
                                case R.id.nextCardButton:
                                    alertDialogDeleteCard();
                                    break;
                            }
                    }
                }
            }
        });



        findViewById(R.id.deleteCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question1)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();
                currentCardDisplayedIndex++;
                if (allFlashcards != null && allFlashcards.size() > 0) {
                    if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                        currentCardDisplayedIndex = allFlashcards.size() - 1;
                        ((TextView) findViewById(R.id.flashcard_question1)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                        ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                    }
                } else {
                    Log.d("State","Empty State. No Flash Cards identified. ");
                    Log.d("Database:-", allFlashcards.toString());

                    // Else show the empty state
                    ((TextView) findViewById(R.id.flashcard_question1)).setText("");
                    ((TextView) findViewById(R.id.flashcard_answer1)).setText("");

                    switch (v.getId()){
                        case R.id.deleteCard:
                            alertDialogDeleteCard();
                            break;
                    }
                }
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
    private void alertDialogNextCard() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("There is only one card. Please create more cards!");
        dialog.setTitle("Error");
//        dialog.setPositiveButton("YES",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,
//                                        int which) {
//                        Toast.makeText(getApplicationContext(),"Yes is clicked", Toast.LENGTH_LONG).show();
//                    }
//                });
        dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Returning to main screen",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
    private void alertDialogDeleteCard() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("There are no more cards left to show. Please create more cards!");
        dialog.setTitle("Info");
//        dialog.setPositiveButton("YES",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,
//                                        int which) {
//                        Toast.makeText(getApplicationContext(),"Yes is clicked", Toast.LENGTH_LONG).show();
//                    }
//                });
        dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Returning to main screen",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

}