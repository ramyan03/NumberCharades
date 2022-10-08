package com.example.numbercharades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartGame extends AppCompatActivity {
    public static final String EXTRA_NUM = "com.example.numbercharades.EXTRA_NUM";
    private Button StartGameButtonEasy;
    private Button StartGameButtonMedium;
    private Button StartGameButtonHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        StartGameButtonEasy = (Button) findViewById(R.id.button4);
        StartGameButtonMedium = (Button) findViewById(R.id.button5);
        StartGameButtonHard = (Button) findViewById(R.id.button6);

        StartGameButtonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlayArea(1);
            }
        });

        StartGameButtonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlayArea(2);
            }
        });

        StartGameButtonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlayArea(3);
            }
        });

    }
    public void openPlayArea(int diffcultyNum){
        Intent intentPlay = new Intent(this,PlayArea.class);
        intentPlay.putExtra(EXTRA_NUM,diffcultyNum);
        startActivity(intentPlay);

    }

}