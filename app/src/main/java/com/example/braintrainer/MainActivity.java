package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int overall = 0;
    int score = 0;
    int randomPlace;
    public void createEq(){
        TextView textViewEQ = findViewById(R.id.textView3);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        int random1 = new Random().nextInt(21);
        int random2 = new Random().nextInt(21);
        randomPlace = new Random().nextInt(4)+1;
        int wrong1 = new Random().nextInt(41);
        int wrong2 = new Random().nextInt(41);
        int wrong3 = new Random().nextInt(41);

        ArrayList<Integer> answers = new ArrayList<Integer>();

        textViewEQ.setText(Integer.toString(random1)+"+"+Integer.toString(random2));

        for (int i = 0; i<4; i++){
            if (i == randomPlace){
                answers.add(random1+random2);
            }else{
                int wrong = new Random().nextInt(41);
                if (wrong == random1+random2){
                    wrong = new Random().nextInt(41);
                }
                answers.add(wrong);

            }
        }

        if ( randomPlace == 1){
            button1.setText(Integer.toString(random1+random2));
            button2.setText(Integer.toString(wrong1));
            button3.setText(Integer.toString(wrong2));
            button4.setText(Integer.toString(wrong3));
        }else if ( randomPlace == 2){
            button2.setText(Integer.toString(random1+random2));
            button1.setText(Integer.toString(wrong1));
            button3.setText(Integer.toString(wrong2));
            button4.setText(Integer.toString(wrong3));
        }else if ( randomPlace == 3){
            button3.setText(Integer.toString(random1+random2));
            button2.setText(Integer.toString(wrong1));
            button1.setText(Integer.toString(wrong2));
            button4.setText(Integer.toString(wrong3));
        }else {
            button4.setText(Integer.toString(random1+random2));
            button2.setText(Integer.toString(wrong1));
            button3.setText(Integer.toString(wrong2));
            button1.setText(Integer.toString(wrong3));
        }
    }

    public void game(){
        final TextView timeText = findViewById(R.id.textView);
        createEq();
        CountDownTimer Timer= new CountDownTimer(30000+100,1000){
            @Override
            public void onTick(long l) {
                int time = (int) l/1000;
                timeText.setText(Integer.toString(time)+"s");
            }
            @Override
            public void onFinish() {
                findViewById(R.id.button5).setVisibility(View.VISIBLE);
                findViewById(R.id.button1).setEnabled(false);
                findViewById(R.id.button2).setEnabled(false);
                findViewById(R.id.button3).setEnabled(false);
                findViewById(R.id.button4).setEnabled(false);

            }
        }.start();
    }

    public void start(View view){
        findViewById(R.id.gridLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.textView).setVisibility(View.VISIBLE);
        findViewById(R.id.textView2).setVisibility(View.VISIBLE);
        findViewById(R.id.button).setVisibility(View.INVISIBLE);
        game();


    }

    public void click(View view){
        TextView scoreText = findViewById(R.id.textView2);
        String clicked = view.getTag().toString();
        if (randomPlace == Integer.parseInt(clicked)){
            score++;
        }
        overall++;
        scoreText.setText(Integer.toString(score)+"/"+Integer.toString(overall));
        createEq();
    }

    public void playAgain(View view){
        final TextView timeText = findViewById(R.id.textView2);
        timeText.setText("0/0");
        score = 0;
        overall = 0;
        findViewById(R.id.button5).setVisibility(View.INVISIBLE);
        findViewById(R.id.button1).setEnabled(true);
        findViewById(R.id.button2).setEnabled(true);
        findViewById(R.id.button3).setEnabled(true);
        findViewById(R.id.button4).setEnabled(true);
        game();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}