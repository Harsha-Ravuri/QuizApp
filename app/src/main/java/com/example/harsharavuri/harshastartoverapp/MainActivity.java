package com.example.harsharavuri.harshastartoverapp;

import android.app.Activity;
import android.content.Context;
import android.util.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.widget.ImageView;

import com.google.android.gms.location.LocationListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class MainActivity extends Activity implements Serializable { //AppCompatActivity

    public static final String EXTRA_MESSAGE = "com.example.harshastartoverapp.MESSAGE";
    public static String DATABASE_CREATED = "QUIZ_DATABASE" ;
    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private String message;
    private String text = "";
    private List<QuestionAnswerSet> allQASets;

    public QuizDatabaseHelper db;
    //public static final Context mainActivityContext =

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            Log.d("STATE", savedInstanceState.toString());
        }
        Log.d("CREATION", "onCreate() MainActivity is being executed");

        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                message = "\n" + location.getLatitude() + "\n" + location.getLongitude();

            }
            /**
             public void onProviderDisabled(String s){
             Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
             startActivity(i);
             }
             */
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
                }, 10);
                return;
            }
        } else {
            configureButton();
        }

        //myImView.setImageResource();

         db = new QuizDatabaseHelper(this);
        //db.deleteAllRecords();

        //db.addQuestionAnswerSet("Where is NCSU?","Raleigh","Cincinnati","Chicago","Seattle");
        //db.addQuestionAnswerSet("Why is this not working?","Code is strange","I am silly","I'm tired","Need sleep");
        //numberOfQuestions++;

        Intent comingBackI = getIntent();
        if(comingBackI.hasExtra("USERDATABASEENTRY")){
            addQuestionToDatabase(comingBackI);
        }

        if(db!= null)
            allQASets = db.getAllQuestionAnswers();

    }

    public void addQuestionToDatabase(Intent i){
        if(i!= null){
            ArrayList<String> userQA = i.getStringArrayListExtra("USERDATABASEENTRY");
            db.addQuestionAnswerSet(userQA.get(0),userQA.get(1),userQA.get(2),userQA.get(3),userQA.get(4));
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configureButton();
                }
                return;
        }


    }

    private void configureButton() {

        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, (android.location.LocationListener) locationListener);
    }

    public void deleteAllRecords(View view){
        db.deleteAllRecords();
    }


    /**
     * Called when the user taps the Send button
     */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, serviceActivity.class);
        ArrayList<String> thisSet = new ArrayList<>();

        for(QuestionAnswerSet qa: allQASets){//
            //String log = "Q: "+qa.getQuestion()+" A1: "+qa.getAnswer()+" A2: "+qa.getChoice1()+" A3: "+qa.getChoice2()+" A4: "+qa.getChoice3();

            ArrayList<String> answerChoices = new ArrayList<>();
            answerChoices.add(qa.getAnswer().getChoice());
            answerChoices.add(qa.getChoice1().getChoice());
            answerChoices.add(qa.getChoice2().getChoice());
            answerChoices.add(qa.getChoice3().getChoice());

            //Collections.shuffle(answerChoices);

            thisSet.add(0,qa.getQuestion());

            thisSet.add(1,answerChoices.get(0));

            thisSet.add(2,answerChoices.get(1));

            thisSet.add(3,answerChoices.get(2));

            thisSet.add(4,answerChoices.get(3));

            intent.putStringArrayListExtra(EXTRA_MESSAGE,thisSet);
            startActivity(intent);
        }

    }

    public void addQuestion(View view){
        // This is the method that the button "add question" does.
        // When the user clicks on it he can go to a seperate
        // activity page and add the question and the answer choices

        Intent intent = new Intent(this, AddAQuestion.class);

        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Context getContext(){
        return this;
    }
}
