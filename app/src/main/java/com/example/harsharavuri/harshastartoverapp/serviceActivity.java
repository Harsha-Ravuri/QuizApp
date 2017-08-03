package com.example.harsharavuri.harshastartoverapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class serviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        ArrayList<String> qaSent = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE);

        TextView question = (TextView) findViewById(R.id.textView3);
        question.setText(qaSent.get(0));

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setText(qaSent.get(1));

        Button button4 = (Button)findViewById(R.id.button4);
        button4.setText(qaSent.get(2));

        Button button5 = (Button)findViewById(R.id.button5);
        button5.setText(qaSent.get(3));

        Button button6 = (Button)findViewById(R.id.button6);
        button6.setText(qaSent.get(4));

        //Intent intent = new Intent(this, QuizDatabaseHelper.class);
        //QuizDatabaseHelper db = new QuizDatabaseHelper(this);

    }

    /**
     * This is the method that is activated when the user chooses an answer.
     * The behavior is the following ->
     *    Is the answer right?
     *    Okay, now say if it's right and go to next question.
     *    Maybe keep score?
     *
     * */
    public void choiceClicked(View view){

    }
}
