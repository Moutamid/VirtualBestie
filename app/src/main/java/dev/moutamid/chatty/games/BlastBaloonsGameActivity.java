package dev.moutamid.chatty.games;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import dev.moutamid.chatty.utilities.Blastview;
import dev.moutamid.chatty.utilities.Utils;
import moutamid.spdf.com.chatty.databinding.ActivityBlastBaloonsGameBinding;

public class BlastBaloonsGameActivity extends AppCompatActivity {

    private ActivityBlastBaloonsGameBinding b;

    private Blastview blastview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityBlastBaloonsGameBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        blastview = Blastview.attach2Window(this);

        YoYo.with(Techniques.Shake).delay(1000).duration(5000).repeat(15).playOn(b.baloon1);
        YoYo.with(Techniques.Wobble).delay(500).duration(5000).repeat(15).playOn(b.baloon2);
        YoYo.with(Techniques.Swing).delay(1300).duration(5000).repeat(15).playOn(b.baloon3);
        YoYo.with(Techniques.Tada).delay(1500).duration(5000).repeat(15).playOn(b.baloon4);
        YoYo.with(Techniques.ZoomInDown).delay(200).duration(5000).repeat(15).playOn(b.baloon5);
        YoYo.with(Techniques.ZoomInLeft).delay(1000).duration(5000).repeat(15).playOn(b.baloon6);
        YoYo.with(Techniques.ZoomInRight).delay(2000).duration(5000).repeat(15).playOn(b.baloon7);
        YoYo.with(Techniques.Flash).delay(800).duration(5000).repeat(15).playOn(b.baloon8);
        YoYo.with(Techniques.Flash).delay(1600).duration(5000).repeat(15).playOn(b.baloon9);

    }

    public void BaloonClicked(View view) {
        ImageView imageView = (ImageView) view;

        blastview.explode(imageView);
        Utils.explodeSound();

    }
}