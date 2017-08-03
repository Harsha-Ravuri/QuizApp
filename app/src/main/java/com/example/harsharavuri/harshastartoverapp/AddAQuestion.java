package com.example.harsharavuri.harshastartoverapp;

/**
 * This class deals with adding a question of the User's choice to the quiz and the database
 *
 * */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;

public class AddAQuestion extends AppCompatActivity implements Serializable{

    private ArrayList<String> userQA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aquestion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //This makes sure we get to this front page to submit the question
        Intent i = getIntent();
    }

    /**
     *  This is called by the "submit" button. The questionA set is submitted
     *  and we go back to the main activity page
     *
     * */
    public void backToMain(View view){
        Intent backI = new Intent(this,MainActivity.class);

        EditText userQString = (EditText) findViewById(R.id.qstring);
        EditText userAString = (EditText) findViewById(R.id.astring);
        EditText userCString1 = (EditText) findViewById(R.id.c1string);
        EditText userCString2 = (EditText) findViewById(R.id.c2string);
        EditText userCString3 = (EditText) findViewById(R.id.c3string);

        String userQs = userQString.getText().toString();
        String userAs = userAString.getText().toString();
        String userCs1 = userCString1.getText().toString();
        String userCs2 = userCString2.getText().toString();
        String userCs3 = userCString3.getText().toString();

        userQA = new ArrayList<>();
        userQA.add(0, userQs);
        userQA.add(1, userAs);
        userQA.add(2, userCs1);
        userQA.add(3, userCs2);
        userQA.add(4, userCs3);

        backI.putStringArrayListExtra("USERDATABASEENTRY", userQA);
        startActivity(backI);

    }
}
