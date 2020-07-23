package com.tdevelopments.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity {
    int locationOfCorrectAnswer;

    ArrayList<Integer> answer = new ArrayList<Integer>();

    Button goButton;
    TextView startTextview ;
    TextView timerTextView;
    TextView scoreTextView;
    TextView textViewResult;
    TextView sumTextView;
    GridLayout gridLayout;
    Button button0 ;
    Button button1 ;
    Button button2 ;
    Button button3 ;

    ConstraintLayout constraintLayout;

    int score = 0;

    int numberOfQuestions = 0 ;


    public void chooseAnswer(View view) {

        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
             String correctAns = "Correct !";
             textViewResult.setVisibility(VISIBLE);
             textViewResult.setText(correctAns);
             score++;
        } else {
            String wrongAns = "wrong !" ;
            textViewResult.setVisibility(VISIBLE);
            textViewResult.setText(wrongAns);
        }
        numberOfQuestions++;

        scoreTextView.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestions));
        newQuestions();
    }

    public void newQuestions() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        answer.clear();

        sumTextView.setText(Integer.toString(a) +  " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        for (int i = 0; i<4; i++) {

            if (i == locationOfCorrectAnswer) {
                answer.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answer.add(wrongAnswer);
            }

        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         button0 = findViewById(R.id.button0);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);


        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        sumTextView = findViewById(R.id.sumTextView);
        gridLayout = findViewById(R.id.gridLayout);
        textViewResult = findViewById(R.id.resultTextView);

        goButton = findViewById(R.id.goButton);
        startTextview = findViewById(R.id.startTextView);

        constraintLayout = findViewById(R.id.Layout);

        sumTextView.setVisibility(INVISIBLE);
        textViewResult.setVisibility(INVISIBLE);
        scoreTextView.setVisibility(INVISIBLE);
        timerTextView.setVisibility(INVISIBLE);
        gridLayout.setVisibility(INVISIBLE);


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goButton.setVisibility(INVISIBLE);
                startTextview.setVisibility(INVISIBLE);

                constraintLayout.setBackgroundColor(Color.RED);


                sumTextView.setVisibility(VISIBLE);
                scoreTextView.setVisibility(VISIBLE);
                timerTextView.setVisibility(VISIBLE);
                gridLayout.setVisibility(VISIBLE);


            }
        });



        newQuestions();

        new CountDownTimer(30000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf( millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {

                textViewResult.setText("Done !");

            }
        }.start();


    }
}
