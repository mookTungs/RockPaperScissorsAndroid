package com.mookt.rockpaperscissors;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static int TIED = 0;
    static int YOU = 1;
    static int COM = 2;
    ImageView youImage;
    ImageView comImage;
    TextView youScore;
    TextView comScore;
    int yourPoint = 0;
    int comPoint = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        youImage = findViewById(R.id.youImage);
        comImage = findViewById(R.id.comImage);
        youScore = findViewById(R.id.youScore);
        comScore = findViewById(R.id.comScore);
        Button newGame = findViewById(R.id.newGame);
        Button rock = findViewById(R.id.rock);
        Button paper = findViewById(R.id.paper);
        Button scissors = findViewById(R.id.scissors);

        newGame.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v){
               youImage.setVisibility(View.GONE);
               comImage.setVisibility(View.GONE);
               youScore.setText("0");
               comScore.setText("0");
               yourPoint = 0;
               comPoint = 0;
           }
        });

        rock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                youImage.setImageResource(R.mipmap.rock);
                youImage.setVisibility(View.VISIBLE);
                runGame(1);
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                youImage.setImageResource(R.mipmap.paper);
                youImage.setVisibility(View.VISIBLE);
                runGame(2);
            }
        });

        scissors.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                youImage.setImageResource(R.mipmap.scissors);
                youImage.setVisibility(View.VISIBLE);
                runGame(3);
            }
        });

    }

    public void runGame(int yourChoice){

        int comChoice = computerChoice();

        if(comChoice == 1){
            comImage.setImageResource(R.mipmap.rock);
        }else if(comChoice == 2){
            comImage.setImageResource(R.mipmap.paper);
        }else if(comChoice == 3){
            comImage.setImageResource(R.mipmap.scissors);
        }
        comImage.setVisibility(View.VISIBLE);

        int win = whoWin(yourChoice, comChoice);
        if(win == YOU){
            yourPoint++;
            youScore.setText(Integer.toString(yourPoint));
        }else if(win == COM){
            comPoint++;
            comScore.setText(Integer.toString(comPoint));
        }

    }

    public int computerChoice(){
        Random rand = new Random();
        return rand.nextInt(3) + 1;
    }

    public int whoWin(int yourChoice, int comChoice){
        int point;
        int result = yourChoice - comChoice;
        if(result == 0) {
            point = TIED;
        } else if(result == 1 || result == -2){
            point = YOU;
        }else if(result == -1 || result == 2) {
            point = COM;
        }else{
            point = 0;
        }
        return point;
    }

}
