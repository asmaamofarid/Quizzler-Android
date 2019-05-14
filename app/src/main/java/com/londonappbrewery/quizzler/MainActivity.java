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
    TextView mTextView;
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
    final int PROGRESS_BAR_INCREMENT = 8 / mQuestionBank.length;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TButton = findViewById(R.id.true_button);
        Fbutton = findViewById(R.id.false_button);
        //true button Listener
        TButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatequestions();
                rightAnwnser(true);


            }
        });

        //false button Listener
        Fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatequestions();
                rightAnwnser(false);

            }
        });
        //get the first question
        mTextView = findViewById(R.id.question_text_view);
        //mindex =0
        mquestion = mQuestionBank[mindex].getQuestionID();
        //mquestion = اول سؤال
        score = findViewById(R.id.score);
        mProgressBar = findViewById(R.id.progress_bar);

    }

    public void rightAnwnser(boolean userlocation) {
        boolean checkanwnser = mQuestionBank[mindex].getAnswer();

        if (userlocation == checkanwnser) {
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            startScore = startScore + 1;
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }


    }



    void updatequestions() {
        if (mindex == (mQuestionBank.length - 1)) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
            mBuilder.setTitle("quiz end");
            mBuilder.setCancelable(false);
            mBuilder.setMessage("your score " + startScore + " Points!");
            mBuilder.setPositiveButton("Close App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            mBuilder.show();
        } else {
            mindex = mindex + 1;
            mquestion = mQuestionBank[mindex].getQuestionID();
            mTextView.setText(mquestion);
            score.setText("Score " + startScore + "/" + mQuestionBank.length);
            mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
            mTextView.setText(mquestion);

        }

    }


}
