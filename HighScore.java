package com.example.numbercharades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighScore extends AppCompatActivity {
    public static final String EXTRA_NUM = "com.example.numbercharades.EXTRA_NUM";
    int[] highScoreArray = new int[3];

    int highScoreVal = 0;

    public static final String SHARED_PREFS = "sharedPrefs";

    public static final String VAL1 = "1";
    public static final String VAL2 = "2";
    public static final String VAL3 = "3";


    private TextView highScore1;
    private TextView highScore2;
    private TextView highScore3;

    private Button menuButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        Intent intent = getIntent();
        highScoreVal = intent.getIntExtra(MainActivity.EXTRA_NUM,0);

        highScore1 = (TextView) findViewById(R.id.textView11);
        highScore2 = (TextView) findViewById(R.id.textView12);
        highScore3 = (TextView) findViewById(R.id.textView13);

        loadData();

        if(highScoreVal > highScoreArray[0]){
            highScoreArray[1] = highScoreArray[0];
            highScoreArray[0] = highScoreVal;
        }else if(highScoreVal> highScoreArray[1]){
            highScoreArray[2] = highScoreArray[1];
            highScoreArray[1] = highScoreVal;
        }else if(highScoreVal> highScoreArray[2]) {
            highScoreArray[2] = highScoreVal;
        }

        highScore1.setText(String.format("Personal best: %d",highScoreArray[0]));
        highScore2.setText(String.format("Second best: %d",highScoreArray[1]));
        highScore3.setText(String.format("Third best: %d",highScoreArray[2]));


        saveData();

        menuButton = (Button) findViewById(R.id.highscoresmenu);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainMenu();
            }
        });

    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(VAL1, highScoreArray[0]);
        editor.putInt(VAL2, highScoreArray[1]);
        editor.putInt(VAL3, highScoreArray[2]);

        editor.commit();

    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        highScoreArray[0] = sharedPreferences.getInt(VAL1, 0);
        highScoreArray[1] = sharedPreferences.getInt(VAL2, 0);
        highScoreArray[2] = sharedPreferences.getInt(VAL3, 0);

    }

    public void mainMenu(){
        Intent intentmainMenu = new Intent(this,MainActivity.class);
        startActivity(intentmainMenu);
    }

}