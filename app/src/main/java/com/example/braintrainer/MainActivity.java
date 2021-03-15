package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv1,tv2;
    Button button;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button playAgain;
    TextView sumTextView;
    TextView score;
    TextView answer;
    TextView time;
    GridLayout gridLayout;
    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            points++;
            answer.setText("Correct");
        }else{
            answer.setText("Wrong");
        }
        q++;
        score.setText(points+"/"+q);
        generate();
    }

    public void start(View view){
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        time.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    int points = 0, q = 0, ans = 0;

    public void generate(){
        Random r  = new Random();
        int a = r.nextInt(51), b = r.nextInt(51);
        int incorrect;
        sumTextView.setText(a + " + " + b);
        locationOfCorrectAnswer = r.nextInt(4);
        answers.clear();
        for (int i=0;i<4;i++){
            if (i == locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                incorrect=r.nextInt(81);
                while (incorrect== a+b) {
                    incorrect= r.nextInt(81);
                }
            answers.add(incorrect);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(View view){
        int points = 0, q = 0;
        answer.setVisibility(View.VISIBLE);
        score.setText(points+"/"+q);
        answer.setText("Start");
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        time.setText("30s");
        gridLayout.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);


        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                time.setText("0s");
                answer.setText("Time's up");
                gridLayout.setVisibility(View.INVISIBLE);
                playAgain.setVisibility(View.VISIBLE);


            }
        }.start();
        generate();

        playAgain.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        button=(Button)findViewById(R.id.button5);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        playAgain = findViewById(R.id.playAgain);
        sumTextView = findViewById(R.id.sumTextView);
        score = findViewById(R.id.scoreTextView);
        time = findViewById(R.id.timerTextView);
        answer = findViewById(R.id.resultTextView);
        gridLayout = findViewById(R.id.gridLayout);
    }
}