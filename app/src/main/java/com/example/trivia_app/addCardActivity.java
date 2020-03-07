package com.example.trivia_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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
                Intent intent = new Intent(addCardActivity.this, MainActivity.class);
                startActivityForResult(intent,100);
                Intent data = new Intent();
                String question = ((EditText) findViewById(R.id.editQuestion)).getText().toString();
                String answer = ((EditText) findViewById(R.id.editAnswer)).getText().toString();
                data.putExtra("newCardQuestion", question);
                data.putExtra("newAnswerQuestion", answer);
                //Log.d(question, "onActivityResult() returned: " );
                setResult(101,data);
                finish();
            }
        });
    }
}
