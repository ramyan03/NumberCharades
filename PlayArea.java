package com.example.numbercharades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import java.util.*;

import org.w3c.dom.Text;

public class PlayArea extends AppCompatActivity {
    public static final String EXTRA_NUM = "com.example.numbercharades.EXTRA_NUM";

    int count = 59;
    int count2 = 5;
    int count3 = 5;
    int score = 0;
    int correctValue = 1;
    int amtHints = 3;

    int diffSet = 0;

    //int[] buttonValueArray = new int[4];
    ArrayList<Integer> buttonValueList = new ArrayList<>();

    private TextView countdown;
    private TextView guess;
    private TextView increment;
    private TextView correctIncorrect;
    private TextView availableHints;
    private TextView hintDisplay;

    private Button playAreaButton1;
    private Button playAreaButton2;
    private Button playAreaButton3;
    private Button playAreaButton4;
    private Button playAreaButton5;
    private Button playAreaButton6;
    private Button hintMenuButton;

    final Random random = new Random();

    Thread thread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_area);

        countdown = (TextView) findViewById(R.id.textView3);
        guess = (TextView) findViewById(R.id.textView4);
        correctIncorrect = (TextView) findViewById(R.id.textView7);
        increment = (TextView) findViewById(R.id.textView8);
        availableHints = (TextView) findViewById(R.id.textView5);
        hintDisplay = (TextView) findViewById(R.id.textView6);

        playAreaButton1= (Button) findViewById(R.id.button7);
        playAreaButton2= (Button) findViewById(R.id.button8);
        playAreaButton3= (Button) findViewById(R.id.button9);
        playAreaButton4= (Button) findViewById(R.id.button10);
        playAreaButton5= (Button) findViewById(R.id.button11);
        playAreaButton6= (Button) findViewById(R.id.button12);

        hintMenuButton = (Button) findViewById(R.id.button13);

        Intent intent = getIntent();
        diffSet = intent.getIntExtra(StartGame.EXTRA_NUM,0);

        switch(diffSet){
            case 1:
                buttonValueList = new ArrayList<>(Arrays.asList(0,0));
                playAreaButton3.setVisibility(View.INVISIBLE);
                playAreaButton4.setVisibility(View.INVISIBLE);
                playAreaButton5.setVisibility(View.INVISIBLE);
                playAreaButton6.setVisibility(View.INVISIBLE);
                break;
            case 2:
                buttonValueList = new ArrayList<>(Arrays.asList(0,0,0,0));
                playAreaButton5.setVisibility(View.INVISIBLE);
                playAreaButton6.setVisibility(View.INVISIBLE);
                break;
            case 3:
                buttonValueList = new ArrayList<>(Arrays.asList(0,0,0,0,0,0));
                break;
        }


        thread = new Thread(){
            @Override
            public void run(){
                try{
                    while(!thread.isInterrupted() && count > 0){
                        if(count2<=0) {
                            increment.setVisibility(View.INVISIBLE);
                            correctIncorrect.setVisibility(View.INVISIBLE);
                        }
                        if(count3<=0){
                            hintDisplay.setVisibility(View.INVISIBLE);
                        }

                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                countdown.setText(String.valueOf(count));
                                count--;
                                count2--;
                                count3--;
                            }
                        });

                        // everything else goes here

                        playAreaButton1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                count2 = 3;
                                checker(buttonValueList.get(0));
                                generate();
                            }
                        });

                        playAreaButton2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                count2 = 3;
                                checker(buttonValueList.get(1));
                                generate();
                            }
                        });

                        playAreaButton3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                count2 = 3;
                                checker(buttonValueList.get(2));
                                generate();

                            }
                        });

                        playAreaButton4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                count2 = 3;
                                checker(buttonValueList.get(3));
                                generate();

                            }
                        });

                        playAreaButton5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                count2 = 3;
                                checker(buttonValueList.get(4));
                                generate();

                            }
                        });

                        playAreaButton6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                count2 = 3;
                                checker(buttonValueList.get(5));
                                generate();

                            }
                        });

                        hintMenuButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                hintOrMenu();
                            }
                        });

                    }
                    gameOver();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        generate();
        thread.start();
    }

    public void generate() {
        if (count >= 5) {
            int lowerBound = random.nextInt(0 + 100) - 100;
            int upperBound = random.nextInt(100 - 0) + 0;
            int correctIndex = random.nextInt(buttonValueList.size());

            for (int i = 0; i < buttonValueList.size(); i++) {
                int tempRandInt = random.nextInt(upperBound - lowerBound) + lowerBound;
                if(!buttonValueList.contains(tempRandInt)){
                    buttonValueList.set(i,tempRandInt);
                }else{
                    i--;
                }
            }
            correctValue = buttonValueList.get(correctIndex);

            guess.setText(String.format("Guess the number between %d and %d", lowerBound, upperBound));
            playAreaButton1.setText(String.valueOf(buttonValueList.get(0)));
            playAreaButton2.setText(String.valueOf(buttonValueList.get(1)));

            if(diffSet==2) {
                playAreaButton3.setText(String.valueOf(buttonValueList.get(2)));
                playAreaButton4.setText(String.valueOf(buttonValueList.get(3)));
            }else if(diffSet==3){
                playAreaButton3.setText(String.valueOf(buttonValueList.get(2)));
                playAreaButton4.setText(String.valueOf(buttonValueList.get(3)));
                playAreaButton5.setText(String.valueOf(buttonValueList.get(4)));
                playAreaButton6.setText(String.valueOf(buttonValueList.get(5)));
            }
        }
        //else{
            //guess.setText("Game Over");
        //}

    }


    public void checker(int buttonValue){
        if(buttonValue == correctValue){
            count+= 6;
            score++;
            increment.setText("+5");
            increment.setTextColor(Color.parseColor("#32CD32"));
            increment.setVisibility(View.VISIBLE);
            correctIncorrect.setText(String.format("Correct: The number was %d", correctValue));
            correctIncorrect.setVisibility(View.VISIBLE);
        }else{
            if(count<=5){
                thread.interrupt();
                countdown.setText("0");
                count-=5;
                gameOver();
            }else{
                count-= 4;
                increment.setText("-5");
                increment.setTextColor(Color.parseColor("#FF0000"));
                increment.setVisibility(View.VISIBLE);
                correctIncorrect.setText(String.format("Incorrect: The number was %d", correctValue));
                correctIncorrect.setVisibility(View.VISIBLE);
            }
        }

    }

    public void hintOrMenu(){
        if(count > 0 && amtHints!= 0){
            int randHint = random.nextInt(3);
            int multiple = 0;
            for(int i = 2; i<=7; i++) {
                if (correctValue % i == 0) {
                    multiple = i;
                }
            }
            if(multiple == 0){
                randHint = 3;
            }
            switch(randHint){
                case 0:
                    hintDisplay.setText(String.format("The correct number is divisible by %d",multiple));
                    break;
                case 1:
                    hintDisplay.setText(String.format("The correct number is a multiple of %d",multiple));
                    break;
                case 2:
                    if(correctValue>0){
                        hintDisplay.setText("The correct number is positive");
                    }else{
                        hintDisplay.setText("The correct number is negative");
                    }
                    break;
                case 3:
                    hintDisplay.setText(String.format("The correct number is only divisible by itself"));
                    break;
            }
            amtHints--;
            availableHints.setText(String.format("Hints available: %d",amtHints));
            hintDisplay.setVisibility(View.VISIBLE);
            count3 = 5;
        }else if(count>0 && amtHints==0){
            hintDisplay.setText("No more hints!");
            hintDisplay.setVisibility(View.VISIBLE);
            count3 = 5;
        } else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_NUM , score);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void gameOver(){
        playAreaButton1.setVisibility(View.INVISIBLE);
        playAreaButton2.setVisibility(View.INVISIBLE);
        playAreaButton3.setVisibility(View.INVISIBLE);
        playAreaButton4.setVisibility(View.INVISIBLE);
        playAreaButton5.setVisibility(View.INVISIBLE);
        playAreaButton6.setVisibility(View.INVISIBLE);
        availableHints.setVisibility(View.INVISIBLE);
        hintDisplay.setVisibility(View.INVISIBLE);
        hintMenuButton.setText("Main menu");
        guess.setText("Game Over");
        correctIncorrect.setText(String.format("Score: %d",score));
        correctIncorrect.setVisibility(View.VISIBLE);

    }





}