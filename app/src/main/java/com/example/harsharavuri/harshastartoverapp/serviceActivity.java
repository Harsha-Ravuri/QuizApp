package com.example.harsharavuri.harshastartoverapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.harsharavuri.harshastartoverapp.R.id.button4;
import static com.example.harsharavuri.harshastartoverapp.R.id.button5;
import static com.example.harsharavuri.harshastartoverapp.R.id.button6;

public class serviceActivity extends AppCompatActivity {

    private String q;
    private String a;
    private String c1;
    private String c2;
    private String c3;

    AnswerChoice a_AC;
    AnswerChoice c1_AC;
    AnswerChoice c2_AC;
    AnswerChoice c3_AC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        ArrayList<String> qaSent = intent.getStringArrayListExtra(MainActivity.EXTRA_MESSAGE);
        ArrayList<String> justAnswerStrings=  new ArrayList<>();

        q = qaSent.get(0);

        a = qaSent.get(1);
        a_AC  = new AnswerChoice(a, true);

        c1 = qaSent.get(2);
        c1_AC  = new AnswerChoice(c1, false);

        c2 = qaSent.get(3);
        c2_AC  = new AnswerChoice(c2, false);

        c3 = qaSent.get(4);
        c3_AC  = new AnswerChoice(c3, false);

        justAnswerStrings.add(a);
        justAnswerStrings.add(c1);
        justAnswerStrings.add(c2);
        justAnswerStrings.add(c3);
        Collections.shuffle(justAnswerStrings);


        TextView question = (TextView) findViewById(R.id.textView3);
        question.setText(q);// Q

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setText(justAnswerStrings.get(0));//A

        Button button4 = (Button)findViewById(R.id.button4);
        button4.setText(justAnswerStrings.get(1));//C1


        Button button5 = (Button)findViewById(R.id.button5);
        button5.setText(justAnswerStrings.get(2));//C2

        Button button6 = (Button)findViewById(R.id.button6);
        button6.setText(justAnswerStrings.get(3));//C3

        //Intent intent = new Intent(this, QuizDatabaseHelper.class);
        //QuizDatabaseHelper db = new QuizDatabaseHelper(this);

    }

    /**
     * This is the method that is activated when the user chooses an answer.
     * The behavior is the following ->
     *    Is the answer right? - If the button clicked on
     *    Okay, now say if it's right and go to next question.
     *    Maybe keep score?
     *
     * */
    public void choiceClicked(View view){
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        TextView letUserKnow = (TextView) findViewById(R.id.textView4);

        if( buttonText == a_AC.getChoice() ){
            //User's answer is correct!
            letUserKnow.setText("You're right!");
        }
        else{
            //User's answer is Wrong!
            letUserKnow.setText("Sorry! That's wrong!");

        }
    }
}
