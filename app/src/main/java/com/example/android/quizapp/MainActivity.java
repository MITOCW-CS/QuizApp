package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initState();
    }

    public void initState() {
        correctAns = 0;
        answer = new boolean[10];
        unchecked = new int[10];
        for (int i = 0; i < 10; i ++)
            unchecked[i] = 0;
    }
    public int getCorrectAns() {
        CheckBox chb1T = (CheckBox) findViewById(R.id.chb1T);
        if (chb1T.isChecked()) {
            if (getResources().getString(R.string.quiz1_answer).equals("true"))
                answer[1] = true;
                correctAns ++;
        }
        CheckBox chb2T = (CheckBox) findViewById(R.id.chb2T);
        if (chb2T.isChecked()) {
            if (getResources().getString(R.string.quiz1_answer).equals("true"))
                answer[2] = true;
                correctAns ++;
        }
        CheckBox chb3T = (CheckBox) findViewById(R.id.chb3T);
        if (chb3T.isChecked()) {
            if (getResources().getString(R.string.quiz1_answer).equals("true"))
                answer[3] = true;
                correctAns ++;
        }

        return correctAns;
    }

    int totalAns = 3;
    int correctAns;
    boolean answer[];
    int unchecked[];

    CheckBox chb1T;
    CheckBox chb1F;
    CheckBox chb2T;
    CheckBox chb2F;
    CheckBox chb3T;
    CheckBox chb3F;

    public void uncheckedWarning() {
        String uncheckedBox = "";
        if (unchecked[1] == 1) uncheckedBox += " 1, ";
        if (unchecked[2] == 2) uncheckedBox += " 2, ";
        if (unchecked[3] == 3) uncheckedBox += " 3 ";
        if (!uncheckedBox.isEmpty()){
            String toastString = "Warning: Quiz " + uncheckedBox + " haven't checked!\n";
            Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
        }
    }

    TextView answer_text_view;

    // Answer button onClick()
    public void getAnswer(View view) {
//        Toast.makeText(getApplicationContext(), String.valueOf(answer[0]), Toast.LENGTH_LONG).show();
        answer_text_view = (TextView) findViewById(R.id.answer_text_view);
        String showAns = String.valueOf(getCorrectAns()) + "/" + String.valueOf(totalAns);
        answer_text_view.setText(showAns);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setFocusable(true);
        progressBar.setMax(totalAns);
        progressBar.setProgress(correctAns);

        chb1T = (CheckBox) findViewById(R.id.chb1T);
        chb1F = (CheckBox) findViewById(R.id.chb1F);
        if (!chb1T.isChecked() && !chb1F.isChecked()) {
            unchecked[1] = 1;
        }

        chb2T = (CheckBox) findViewById(R.id.chb2T);
        chb2F = (CheckBox) findViewById(R.id.chb2F);
        if (!chb2T.isChecked() && !chb2F.isChecked()) {
            unchecked[2] = 2;
        }

        chb3T = (CheckBox) findViewById(R.id.chb3T);
        chb3F = (CheckBox) findViewById(R.id.chb3F);
        if (!chb3T.isChecked() && !chb3F.isChecked()) {
            unchecked[3] = 3;
        }
        uncheckedWarning();

        String quiz1 = "InCorrect", quiz2 = "InCorrect", quiz3 = "InCorrect";
        if (answer[1]) quiz1 = "Correct";
        if (answer[2]) quiz2 = "Correct";
        if (answer[3]) quiz3 = "Correct";
        TextView ans_quiz1 = (TextView) findViewById(R.id.ans_quiz1);
        ans_quiz1.setText(quiz1);
        TextView ans_quiz2 = (TextView) findViewById(R.id.ans_quiz2);
        ans_quiz2.setText(quiz2);
        TextView ans_quiz3 = (TextView) findViewById(R.id.ans_quiz3);
        ans_quiz3.setText(quiz3);

        // Reset state of all components after terms
        initState();
    }

    // Reset button onClick()
    final String LOG_TAG = "RESET_BUTTON";
    public void resetState(View view) {
        try {
            // Checkbox reset
            chb1T.setChecked(false);
            chb1F.setChecked(false);

            chb2T.setChecked(false);
            chb2F.setChecked(false);

            chb3T.setChecked(false);
            chb3F.setChecked(false);

            // Textview
            answer_text_view.setText("");
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
            progressBar.setProgress(0);
            Toast.makeText(getApplicationContext(), "App has been reseted!", Toast.LENGTH_SHORT).show();

        } catch (Exception ex) {
            Log.e(LOG_TAG, ex.getMessage());
        }
    }
}