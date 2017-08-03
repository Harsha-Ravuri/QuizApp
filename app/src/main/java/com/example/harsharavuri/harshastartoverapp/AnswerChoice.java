package com.example.harsharavuri.harshastartoverapp;

/**
 * Created by Harsha Ravuri on 7/17/2017.
 *
 * An AnswerChoice is a String that is the answerchoice and a
 * boolean which states whether the answer
 * is correct.
 * (Note: It should be correct or incorrect in RELATIONSHIP
 * to a question but this doesn't have that functionality yet)
 *
 */

public class AnswerChoice {
    private String choice;
    private boolean correct;

    public AnswerChoice(String choice, boolean correct) {
        this.choice = choice;
        this.correct = correct;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
