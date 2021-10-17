package dev.moutamid.chatty.games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
 import dev.moutamid.chatty.databinding.ActivityDifferentObjectsGameBinding;

public class DifferentObjectsGameActivity extends AppCompatActivity {

    ActivityDifferentObjectsGameBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityDifferentObjectsGameBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        MyOnTouchListener balloonlistener = new MyOnTouchListener(b.balloonLottie);
        MyOnTouchListener jumplistener = new MyOnTouchListener(b.jumpLottie);
        MyOnTouchListener geometrylistener = new MyOnTouchListener(b.geometryLottie);
        MyOnTouchListener orangelistener = new MyOnTouchListener(b.orangeLottie);

        b.balloonLottie.setOnTouchListener(balloonlistener);
        b.jumpLottie.setOnTouchListener(jumplistener);
        b.geometryLottie.setOnTouchListener(geometrylistener);
        b.orangeLottie.setOnTouchListener(orangelistener);

    }

    private class MyOnTouchListener implements View.OnTouchListener {

        LottieAnimationView lottieAnimationView;

        public MyOnTouchListener(LottieAnimationView lottieAnimationView) {
            this.lottieAnimationView = lottieAnimationView;
        }

        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

//                case MotionEvent.ACTION_MOVE:
                    // touch down code
                    lottieAnimationView.playAnimation();
                    break;

                case MotionEvent.ACTION_UP:
                    // touch up code
                    lottieAnimationView.pauseAnimation();
                    break;
            }
            return true;
        }
    }

}