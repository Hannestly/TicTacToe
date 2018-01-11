package com.hyonlee.connectthree;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //every odd number = yellow, every even number = red
    public int counter = 0;

    //0 = yellow's turn, 1 = red's turn
    public int turn = 0;

    // each represent a spot on the board, 2 meaning unplayed box
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningState = {{0,3,6},{1,4,7},{2,5,8},{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6}};
    boolean gameIsActive = true;

    public void countReceiver(){
        counter ++;
        TextView countView = (TextView) findViewById(R.id.moveNum);
        countView.setText(Integer.toString(counter));
    }

    public void dropIn(View view){


        ImageView box = (ImageView) view;

        int tappedCounter = (Integer.parseInt(box.getTag().toString()))-1;
        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = turn;
            box.setTranslationY(-1000f);

            if (turn == 0) {
                box.setImageResource(R.drawable.yellow);
                box.animate().translationYBy(1000f).rotation(3600).setDuration(300);
                countReceiver();
                turn = 1;

            } else {
                box.setImageResource(R.drawable.red);
                box.animate().translationYBy(1000f).rotation(3600).setDuration(300);
                countReceiver();
                turn = 0;
            }

        }

        for(int[] winningPosition : winningState){
            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                gameIsActive = false;
                LinearLayout winLayout = (LinearLayout) findViewById(R.id.winLayout);
                TextView winText = (TextView) findViewById(R.id.winText);
                if(gameState[winningPosition[0]] == 0){
                    winText.setText("Yellow player has won!");

                }else{
                    winText.setText("Red player has won!");
                }
                ViewCompat.setTranslationZ(winLayout,1.0f);
                winLayout.setAlpha(1f);
                Log.i("counter", Integer.toString(counter));
            }
        }
        boolean gameIsOver = true;
        for ( int counterState: gameState) {

            if (counterState == 2) {
                gameIsOver = false;
            }
        }
            if (gameIsOver){
                gameIsActive = false;

                LinearLayout winLayout = (LinearLayout) findViewById(R.id.winLayout);
                TextView winText = (TextView) findViewById(R.id.winText);
                winText.setText("The game is a draw");
                winLayout.setAlpha(1f);
                ViewCompat.setTranslationZ(winLayout,1.0f);
                Log.i("Counter", Integer.toString(counter));
            }

    }

    public void refresh(View view){
        turn = 0;
        for(int i = 0; i< gameState.length; i++){
            gameState[i] = 2;
        }
        gameIsActive = true;
        LinearLayout winLayout = (LinearLayout) findViewById(R.id.winLayout);
        winLayout.setAlpha(0f);
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i = 0; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
