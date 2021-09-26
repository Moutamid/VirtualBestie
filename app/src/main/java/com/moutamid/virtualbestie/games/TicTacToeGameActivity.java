package com.moutamid.virtualbestie.games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moutamid.virtualbestie.R;

public class TicTacToeGameActivity extends AppCompatActivity {

    //0 is Tick and 1 is Cross 2 is Empty

    int activePlayer = 0;

    boolean activeGame = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void Tapped(View view) {

        ImageView counter = (ImageView) view;

        Log.i("Tag", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && activeGame) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.tick);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.cross);

                activePlayer = 0;

            }

            counter.animate().alpha(1).translationYBy(1500).rotation(3600).setDuration(900);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    activeGame = false;

                    String winner = "";

                    if (activePlayer == 1) {

                        winner = "Tick";

                    } else {

                        winner = "Cross";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has Won the Game!");

                    winnerTextView.setVisibility(View.VISIBLE);

                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playGameAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        winnerTextView.setVisibility(View.INVISIBLE);

        playAgainButton.setVisibility(View.INVISIBLE);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        ImageView counter1 = (ImageView) findViewById(R.id.imageView1);
        ImageView counter2 = (ImageView) findViewById(R.id.imageView2);
        ImageView counter3 = (ImageView) findViewById(R.id.imageView3);
        ImageView counter4 = (ImageView) findViewById(R.id.imageView4);
        ImageView counter5 = (ImageView) findViewById(R.id.imageView5);
        ImageView counter6 = (ImageView) findViewById(R.id.imageView6);
        ImageView counter7 = (ImageView) findViewById(R.id.imageView7);
        ImageView counter8 = (ImageView) findViewById(R.id.imageView8);
        ImageView counter9 = (ImageView) findViewById(R.id.imageView9);

        counter1.setImageDrawable(null);
        counter2.setImageDrawable(null);
        counter3.setImageDrawable(null);
        counter4.setImageDrawable(null);
        counter5.setImageDrawable(null);
        counter6.setImageDrawable(null);
        counter7.setImageDrawable(null);
        counter8.setImageDrawable(null);
        counter9.setImageDrawable(null);

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;
        }

        activePlayer = 0;

        activeGame = true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_game);
    }
}