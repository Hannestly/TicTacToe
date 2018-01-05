package com.hyonlee.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //every odd number = yellow, every even number = red
    public int counter = 1;

    //0 = yellow's turn, 1 = red's turn
    public int turn = 0;

    // each represent a spot on the board, 2 meaning unplayed box
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningState = {{0,3,6},{1,4,7},{2,5,8},{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){


        ImageView box = (ImageView) view;

        int tappedCounter = (Integer.parseInt(box.getTag().toString()))-1;
        System.out.println(Integer.toString(tappedCounter));
        if (gameState[tappedCounter] == 2) {
            gameState[tappedCounter] = turn;
            box.setTranslationY(-1000f);

            if (turn == 0) {
                box.setImageResource(R.drawable.yellow);
                box.animate().translationYBy(1000f).rotation(3600).setDuration(300);
                counter++;
                turn = 1;

            } else {
                box.setImageResource(R.drawable.red);
                box.animate().translationYBy(1000f).rotation(3600).setDuration(300);
                counter++;
                turn = 0;
            }

        }else{
            Toast.makeText(getApplicationContext(), "The spot is occupied, please select another tile", Toast.LENGTH_SHORT).show();
        }

        for(int[] winningPosition : winningState){
            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                if(gameState[winningPosition[0]] == 0){
                    Toast.makeText(getApplicationContext(), "Player Yellow has won!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Player Red has won!", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
