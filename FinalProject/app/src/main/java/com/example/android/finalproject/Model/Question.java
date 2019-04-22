package com.example.android.finalproject.Model;

public class Question {

    String question;
    String a, b, c, answer;

    public Question(String question, String choice1,
                    String choice2, String choice3, String answer) {
        this.question = question;
        this.a = choice1;
        this.b = choice2;
        this.c = choice3;
        this.answer = answer;
    }
}
