package com.example.jimmy.mathskills;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.*;

public class MainActivity extends AppCompatActivity {

    Boolean addproduct;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView product;
    Button productButton;
    Button addButton;
    TextView resultTextView;
    TextView pointsTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    TextView timerTextView;
    Button productplayAgainButton;
    Button addplayAgainButton;
    RelativeLayout gameRelativeLayout;
    GridLayout buttonLayout;
    float percent;


    public void productPlayAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        productplayAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                buttonLayout.setVisibility(View.VISIBLE);
                product.setVisibility(View.VISIBLE);
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                productplayAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score:" + percent + "%\n Questions:" + Integer.toString(score) + "/" + numberOfQuestions);
                buttonLayout.setVisibility(View.INVISIBLE);
                product.setVisibility(View.INVISIBLE);

            }
        }.start();
    }

    public void addPlayAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        addplayAgainButton.setVisibility(View.INVISIBLE);
        generateAddQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                buttonLayout.setVisibility(View.VISIBLE);
                product.setVisibility(View.VISIBLE);
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                addplayAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score:" + percent + "%\n Questions:" + Integer.toString(score) + "/" + numberOfQuestions);
                buttonLayout.setVisibility(View.INVISIBLE);
                product.setVisibility(View.INVISIBLE);

            }
        }.start();
    }

    public void generateQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(13);
        int b = rand.nextInt(13);
        int incorrectAnswer;

        product.setText(a + " x " + b);
        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i=0; i<4;i++){

            if(i == locationOfCorrectAnswer){
                answers.add(a*b);
            }
            else{

                incorrectAnswer = rand.nextInt((a*b)+4);
                while (incorrectAnswer == a*b) {
                    incorrectAnswer = rand.nextInt((a*b)+4);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
        addproduct = true;

    }

    public void generateAddQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(13);
        int b = rand.nextInt(13);
        int incorrectAnswer;

        product.setText(a + " + " + b);
        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i=0; i<4;i++){

            if(i == locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else{

                incorrectAnswer = rand.nextInt(a+b);
                while (incorrectAnswer == a+b) {
                    incorrectAnswer = rand.nextInt(a+b);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
        addproduct = false;

    }



    public void start(View view) {

        switch (view.getId()) {

            case R.id.productButton:
                productButton.setVisibility(View.INVISIBLE);
                addButton.setVisibility(View.INVISIBLE);
                gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
                productPlayAgain(findViewById(R.id.productPlayAgain));


                break;

            case R.id.addButton:
                productButton.setVisibility(View.INVISIBLE);
                addButton.setVisibility(View.INVISIBLE);
                gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
                addPlayAgain(findViewById(R.id.addPlayAgain));
                break;


        }
    }

    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.bringToFront();
            resultTextView.setText("Correct!");
            //resultTextView.setBackgroundColor(-16711936);

        } else {
            resultTextView.bringToFront();
            resultTextView.setText("Incorrect!");
            //resultTextView.setBackgroundColor(-65536);
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + numberOfQuestions);
        percent = Math.round((score/(float)numberOfQuestions)*100.0);             //get the percentage recieved
        if(addproduct == true) {
            generateQuestion();
        }
        else{
            generateAddQuestion();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productButton = (Button) findViewById(R.id.productButton);
        addButton = (Button) findViewById(R.id.addButton);
        product = (TextView) findViewById(R.id.productTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        productplayAgainButton = (Button) findViewById(R.id.productPlayAgain);
        addplayAgainButton = (Button) findViewById(R.id.addPlayAgain);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);
        buttonLayout = (GridLayout) findViewById(R.id.buttonLayout);


    }
}
