package com.moutamid.virtualbestie.games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;

import com.moutamid.virtualbestie.R;
import com.moutamid.virtualbestie.databinding.ActivityGamesListBinding;
import com.moutamid.virtualbestie.utilities.Utils;

public class GamesListActivity extends AppCompatActivity {
    private static final String TAG = "GamesListActivity";
    private Context context = GamesListActivity.this;

    private ActivityGamesListBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityGamesListBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        AudioManager am =
                (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        am.setStreamVolume(
                AudioManager.STREAM_MUSIC,
                am.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                0);

        b.ticTacToeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, TicTacToeGameActivity.class));
                Utils.clickSound();
            }
        });

        b.balloonCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, BlastBaloonsGameActivity.class));
                Utils.clickSound();

            }
        });

        b.objectsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, DifferentObjectsGameActivity.class));
                Utils.clickSound();

            }
        });

        b.triviaCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, DifferentObjectsGameActivity.class));
                Utils.clickSound();

            }
        });

    }
}