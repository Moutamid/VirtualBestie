package dev.moutamid.chatty;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import dev.moutamid.chatty.activities.HomeActivity;
import dev.moutamid.chatty.activities.MainActivity;
import dev.moutamid.chatty.utilities.Constants;
import dev.moutamid.chatty.utilities.Utils;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().getDecorView().getWindowInsetsController().hide(
                    android.view.WindowInsets.Type.statusBars()
                            | android.view.WindowInsets.Type.navigationBars());
        }

        // Changing the color of status bar
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.gradientOrange));
        /*if (!Utils.getBoolean(Constants.IS_LOGGED_IN, false)) {
            //USER IS NOT LOGGED IN
            finish();
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            return;
        }

        startActivity(new Intent(SplashActivity.this, HomeActivity.class));*/

    }
}
