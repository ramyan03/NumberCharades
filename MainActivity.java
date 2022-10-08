package com.example.numbercharades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_NUM = "com.example.numbercharades.EXTRA_NUM";
    private Button startGameButton;
    private Button highScoreButton;
    private Button howToPlay;
    private TextView test;
    int highScoreVal = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameButton = (Button) findViewById(R.id.button1);
        highScoreButton = (Button) findViewById(R.id.button2);
        howToPlay = (Button) findViewById(R.id.button3);

        Intent intent = getIntent();
        highScoreVal = intent.getIntExtra(PlayArea.EXTRA_NUM,0);

        //test = (TextView) findViewById(R.id.textView12);
        //test.setText(highScoreVal);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStartGame();
            }
        });

        highScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHighScore();
            }
        });

        howToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHowToPlay();
            }
        });


    }
    public void openStartGame(){
        Intent intentStartGame = new Intent(this,StartGame.class);
        startActivity(intentStartGame);
    }

    public void openHighScore(){
        Intent intentHighScore = new Intent(this,HighScore.class);
        intentHighScore.putExtra(EXTRA_NUM,highScoreVal);
        startActivity(intentHighScore);
    }

    public void openHowToPlay(){
        Intent intentHowToPlay = new Intent(this,HowToPlay.class);
        startActivity(intentHowToPlay);
    }









}