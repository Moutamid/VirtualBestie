package com.moutamid.virtualbestie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.moutamid.virtualbestie.R;
import com.moutamid.virtualbestie.databinding.ActivityStoriesBinding;

public class StoriesActivity extends AppCompatActivity {
    private ActivityStoriesBinding b;

    String[] mobileArray = {"Android", "IPhone", "WindowsMobile", "Blackberry",
            "WebOS", "Ubuntu", "Windows7", "Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityStoriesBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, mobileArray);

//        ListView listView = (ListView) findViewById(R.id.mobile_list);
        b.mobileList.setAdapter(adapter);

        b.mobileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                b.currentText.setText(mobileArray[i]);
                isShown = true;
                b.mobileList.setVisibility(View.GONE);
                b.currentText.setVisibility(View.VISIBLE);
                b.backImg.setVisibility(View.VISIBLE);
            }
        });

    }

    private boolean isShown = false;

    @Override
    protected void onDestroy() {

        if (isShown) {
            isShown = false;
            b.mobileList.setVisibility(View.VISIBLE);
            b.currentText.setVisibility(View.GONE);
            b.backImg.setVisibility(View.GONE);

        } else super.onDestroy();
    }
}