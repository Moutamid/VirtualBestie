package com.moutamid.virtualbestie.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.moutamid.virtualbestie.databinding.ActivityHomeBinding;
import com.moutamid.virtualbestie.games.GamesListActivity;
import com.moutamid.virtualbestie.utilities.Constants;
import com.moutamid.virtualbestie.utilities.Utils;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private Context context = HomeActivity.this;

    private ActivityHomeBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        b = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        if (!Utils.getBoolean(Constants.IS_LOGGED_IN, false)){
            //USER IS NOT LOGGED IN
            finish();
            startActivity(new Intent(context, MainActivity.class));
        }

        b.userNameTxt.setText(Utils.getString(Constants.USER_NAME));

        b.animator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.Tada)
                        .duration(700)
                        .withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                Utils.showSuicidalDialog();
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        })
                        .playOn(b.animator);
            }
        });

        b.gamesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, GamesListActivity.class));
            }
        });

    }
}