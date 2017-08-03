package com.example.harsharavuri.harshastartoverapp; /**
 * Created by Harsha Ravuri on 7/8/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuizDatabaseHelper extends SQLiteOpenHelper implements Serializable {

    private static final int DATABASE_VERSION = 2;
    private static final String DICTIONARY_TABLE_NAME = "QuizStrings";
    private static final String COLUMN1 = "Questions" ;
    private static final String COLUMN2 = "Answers" ;
    private static final String COLUMN3 = "Choice1" ;
    private static final String COLUMN4 = "Choice2" ;
    private static final String COLUMN5 = "Choice3" ;
    String[] columns = {
            COLUMN1,
            COLUMN2,
            COLUMN3,
            COLUMN4,
            COLUMN5
    };
    private static final String KEY_DEFINITION = "PRIMARY KEY" ;
    private static final String DATABASE_NAME ="HarshaRandom Quiz.db" ;
    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" +
                    COLUMN1 + " TEXT, " +
                    COLUMN2 + " TEXT, " +
                    COLUMN3 + " TEXT, " +
                    COLUMN4 + " TEXT, " +
                    COLUMN5 + " TEXT, " +
                    KEY_DEFINITION + " ("+ COLUMN1 + "));";

    private static final String DICTIONARY_TABLE_DELETE = "DELETE FROM "+DICTIONARY_TABLE_NAME + " ;";

    QuizDatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE IF EXISTS " + DICTIONARY_TABLE_NAME);
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }

    public void deleteAllRecords(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DICTIONARY_TABLE_DELETE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DICTIONARY_TABLE_NAME);
        onCreate(db);
    }

    public void addQuestionAnswerSet( String q, String a1, String a2, String a3, String a4){

        //Log.d("TAG:","The actual SQL command being used(current method -> addQuestionAnswerSet): "+ DICTIONARY_TABLE_CREATE );
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //key is the column name in the database, and value is the value to store in that column.
        values.put(COLUMN1, q );
        values.put(COLUMN2, a1);
        values.put(COLUMN3, a2);
        values.put(COLUMN4, a3);
        values.put(COLUMN5, a4);

        db.insert(DICTIONARY_TABLE_NAME,null,values);
        db.close();
    }

    public QuestionAnswerSet getQASet(SQLiteDatabase db){
        db = this.getReadableDatabase();
        Cursor myC = db.query(
                DICTIONARY_TABLE_NAME,  // The table to query
                columns,                // The columns to return
                "=?",                   // The columns for the WHERE clause
                new String[]{(String.valueOf(COLUMN1))}, // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                "DESC");                // The sort order
        if( myC != null ){
            myC.moveToFirst();
        }

        String q = myC.getString(0);// Get question
        AnswerChoice rightAnswer = new AnswerChoice(myC.getString(1),true); // get the right answer choice
        AnswerChoice c1 = new AnswerChoice(myC.getString(2), false); // get the wrong answer choice
        AnswerChoice c2 = new AnswerChoice(myC.getString(3), false); // get the wrong answer choice
        AnswerChoice c3 = new AnswerChoice(myC.getString(4), false);// get the wrong answer choice
        QuestionAnswerSet thisQASet = new QuestionAnswerSet(q, rightAnswer, c1, c2, c3);

        return thisQASet;
    }

    public List<QuestionAnswerSet> getAllQuestionAnswers(){
        List<QuestionAnswerSet> questionAnswerSetList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DICTIONARY_TABLE_NAME ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor myC = db.rawQuery(selectQuery, null);

        if(myC.moveToFirst()){
            do{
                String q = myC.getString(0);// Get question
                AnswerChoice rightAnswer = new AnswerChoice(myC.getString(1),true); // get the right answer choice
                AnswerChoice c1 = new AnswerChoice(myC.getString(2), false); // get the wrong answer choice
                AnswerChoice c2 = new AnswerChoice(myC.getString(3), false); // get the wrong answer choice
                AnswerChoice c3 = new AnswerChoice(myC.getString(4), false);// get the wrong answer choice
                QuestionAnswerSet thisQASet = new QuestionAnswerSet(q, rightAnswer, c1, c2, c3);

                questionAnswerSetList.add(thisQASet);


            }while(myC.moveToNext());
        }

        return questionAnswerSetList;
    }

}
