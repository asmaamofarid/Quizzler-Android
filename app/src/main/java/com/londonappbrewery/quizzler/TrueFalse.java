package com.londonappbrewery.quizzler;

public class TrueFalse {

    int questionID;
    boolean answer;

    public TrueFalse(int id,boolean ans){
        questionID=id;
        answer=ans;

    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }
    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
