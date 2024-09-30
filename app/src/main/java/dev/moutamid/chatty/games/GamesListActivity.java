package dev.moutamid.chatty.games;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import dev.moutamid.chatty.utilities.Utils;
import moutamid.spdf.com.chatty.R;
import moutamid.spdf.com.chatty.databinding.ActivityGamesListBinding;

public class GamesListActivity extends AppCompatActivity {
    private static final String TAG = "GamesListActivity";
    private Context context = GamesListActivity.this;

    private ActivityGamesListBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityGamesListBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.firstLayout.setStartColor(R.color.gradientViolet)
                .setEndColor(R.color.gradientOrange)
                .setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);

        b.secondLayout.setStartColor(R.color.lightYelllow)
                .setEndColor(R.color.orangee)
                .setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);

        b.thirdLayout.setStartColor(R.color.pinkee)
                .setEndColor(R.color.otherOrangeeee)
                .setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);

        b.forthLayout.setStartColor(R.color.magentaaaa)
                .setEndColor(R.color.blueeee)
                .setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);

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
                startActivity(new Intent(context, TriviaGameActivity.class));
                Utils.clickSound();

            }
        });

    }
}