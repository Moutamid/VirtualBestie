package com.moutamid.virtualbestie.games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.moutamid.virtualbestie.R;
import com.moutamid.virtualbestie.databinding.ActivityBlastBaloonsGameBinding;
import com.moutamid.virtualbestie.databinding.ActivityGamesListBinding;
import com.moutamid.virtualbestie.utilities.Blastview;
import com.moutamid.virtualbestie.utilities.Utils;

public class BlastBaloonsGameActivity extends AppCompatActivity {

    private ActivityBlastBaloonsGameBinding b;

    private Blastview blastview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityBlastBaloonsGameBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        blastview = Blastview.attach2Window(this);

        YoYo.with(Techniques.Shake).delay(1000).duration(1000).repeat(5).playOn(b.baloon1);
        YoYo.with(Techniques.Wobble).delay(500).duration(1000).repeat(5).playOn(b.baloon2);
        YoYo.with(Techniques.Swing).delay(1300).duration(1000).repeat(5).playOn(b.baloon3);
        YoYo.with(Techniques.Tada).delay(1500).duration(1000).repeat(5).playOn(b.baloon4);
        YoYo.with(Techniques.ZoomInDown).delay(200).duration(1000).repeat(5).playOn(b.baloon5);
        YoYo.with(Techniques.ZoomInLeft).delay(1000).duration(1000).repeat(5).playOn(b.baloon6);
        YoYo.with(Techniques.ZoomInRight).delay(2000).duration(1000).repeat(5).playOn(b.baloon7);
        YoYo.with(Techniques.DropOut).delay(800).duration(1000).repeat(5).playOn(b.baloon8);
        YoYo.with(Techniques.Flash).delay(1600).duration(1000).repeat(5).playOn(b.baloon9);

    }

    public void BaloonClicked(View view) {
        ImageView imageView = (ImageView) view;

        blastview.explode(imageView);
        Utils.explodeSound();

    }
}