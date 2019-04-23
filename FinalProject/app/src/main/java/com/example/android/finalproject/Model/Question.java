package com.example.android.finalproject.Model;

public class Question {

    String question;
    String a, b, c, answer;

    public Question(){}

    public Question(String question, String choice1,
                    String choice2, String choice3, String answer) {
        this.question = question;
        this.a = choice1;
        this.b = choice2;
        this.c = choice3;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}


