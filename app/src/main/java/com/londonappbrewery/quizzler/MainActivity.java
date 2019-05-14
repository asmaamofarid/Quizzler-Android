package com.londonappbrewery.quizzler;

import android.animation.TimeAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare member variables here:
    Button TButton, Fbutton;
    TextView mTextView,qanswer;
    int mindex;
    int mquestion;
    TextView score;
    ProgressBar mProgressBar;
    int startScore = 0;


    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13, true)
    };
    // TODO: Declare constants here
    final int PROGRESS_BAR_INCREMENT =   (int)Math.ceil(100/mQuestionBank.length);

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            startScore=savedInstanceState.getInt("ScoreKey");
            mindex=savedInstanceState.getInt("IndexKey");

        }else{
            startScore=0;
            mindex=0;
        }

        TButton = findViewById(R.id.true_button);
        Fbutton = findViewById(R.id.false_button);
        mProgressBar = findViewById(R.id.progress_bar);
        mTextView = findViewById(R.id.question_text_view);
        score = findViewById(R.id.score);
        qanswer=findViewById(R.id.qanser);

        mquestion = mQuestionBank[mindex].getQuestionID();
        mTextView.setText(mquestion);
        score.setText("Score " + startScore + "/" + mQuestionBank.length);




        //true button Listener
        TButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightAnwnser(true);
                updatequestions();
            }
        });

        //false button Listener
        Fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightAnwnser(false);
                updatequestions();
            }
        });
        //get the first question
        //mindex =0
        //mquestion = اول سؤال

    }


    public void rightAnwnser(boolean userlocation) {
        boolean checkanwnser = mQuestionBank[mindex].getAnswer();

        if (userlocation == checkanwnser) {

            qanswer.setText(R.string.correct_toast);
            startScore = startScore + 1;
            score.setText("Score " + startScore + "/" + mQuestionBank.length);


        } else {
            qanswer.setText(R.string.incorrect_toast);
        }

    }

    void updatequestions() {
        mTextView.setText(mquestion);
        if (mindex == (mQuestionBank.length - 1)) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
            mBuilder.setTitle("quiz end");
            mBuilder.setCancelable(false);
            mBuilder.setMessage("your score " + (startScore) + " Points!");
            mBuilder.setPositiveButton("Close App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            mBuilder.show();
        } else {
            mquestion = mQuestionBank[mindex].getQuestionID();
            mindex = mindex + 1;
            mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);

        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("ScoreKey", startScore);
        outState.putInt("IndexKey",mindex);

    }


}
