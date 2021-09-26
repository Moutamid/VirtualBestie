package com.moutamid.virtualbestie.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.moutamid.virtualbestie.databinding.ActivityMainBinding;
import com.moutamid.virtualbestie.utilities.Constants;
import com.moutamid.virtualbestie.utilities.Utils;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main);

        binding.proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.nameEditText.getText().toString().isEmpty()) {
                    Utils.toast("Please enter your name!");
                    return;
                }

                String name = binding.nameEditText.getText().toString().trim();
                String gender = "null";

                if (binding.maleRadioBtn.isChecked())
                    gender = binding.maleRadioBtn.getText().toString();

                if (binding.femaleRadioBtn.isChecked())
                    gender = binding.femaleRadioBtn.getText().toString();

                Utils.store(Constants.USER_NAME, name);
                Utils.store(Constants.USER_GENDER, gender);
                Utils.store(Constants.IS_LOGGED_IN, true);

                finish();
                startActivity(new Intent(MainActivity.this, HomeActivity.class));


            }
        });
    }
}