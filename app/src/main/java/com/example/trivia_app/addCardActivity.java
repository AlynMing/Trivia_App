package com.example.trivia_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class addCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card2);

        findViewById(R.id.cancelNewCardButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addCardActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                String question = ((EditText) findViewById(R.id.editQuestion)).getText().toString();
                String answer = ((EditText) findViewById(R.id.editAnswer)).getText().toString();
                data.putExtra("newCardQuestion", question);
                data.putExtra("newAnswerQuestion", answer);
                //Log.d(question, "save button clicked and question data outputted: " );
                //above successively returned
                setResult(RESULT_OK,data);
                finish();
            }
        });
    }
}
