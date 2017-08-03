package com.example.harsharavuri.harshastartoverapp;

/**
 * Created by Harsha Ravuri on 7/11/2017.
 *
 * A QuestionAnswerSet is a question and a set of AnswerChoices
 *
 */

public class QuestionAnswerSet {

    private String question;
    private AnswerChoice answer;
    private AnswerChoice choice1;
    private AnswerChoice choice2;
    private AnswerChoice choice3;

    public QuestionAnswerSet(String question, AnswerChoice answer, AnswerChoice choice1, AnswerChoice choice2, AnswerChoice choice3) {
        this.question = question;
        this.answer = answer;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public AnswerChoice getChoice1() {
        return choice1;
    }

    public void setChoice1(AnswerChoice choice1) {
        this.choice1 = choice1;
    }

    public AnswerChoice getChoice2() {
        return choice2;
    }

    public void setChoice2(AnswerChoice choice2) {
        this.choice2 = choice2;
    }

    public AnswerChoice getChoice3() {
        return choice3;
    }

    public void setChoice3(AnswerChoice choice3) {
        this.choice3 = choice3;
    }

    public AnswerChoice getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerChoice answer) {
        this.answer = answer;
    }
}
