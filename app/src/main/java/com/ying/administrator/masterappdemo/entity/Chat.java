package com.ying.administrator.masterappdemo.entity;

import java.io.Serializable;

public class Chat implements Serializable {
    private String question;
    private String answer;

    public Chat(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
