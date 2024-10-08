package dev.moutamid.chatty.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import dev.moutamid.chatty.utilities.Constants;
import moutamid.spdf.com.chatty.R;
import moutamid.spdf.com.chatty.databinding.ActivityStoriesBinding;

public class StoriesActivity extends AppCompatActivity {
    private static final String TAG = "StoriesActivity";
    private Context context = StoriesActivity.this;

    private ActivityStoriesBinding b;

    private String storiesSuffix = "\n\nREFERENCE:\n" +
            "\n" +
            "https://l.messenger.com/l.php?u=https%3A%2F%2Fwww.beyondblue.org.au%2Fwho-does-it-affect%2Fpersonal-stories%3Fcategory%3D8e783b15-0e60-4281-8548-a2ac69988baf&h=AT2uX_7z1s-xZ4WLd-nS1DrDVcDIxR4ULC2lhxhr91vdeCXhKyVMDLPo7iqyzVh8CH5m4iHbhOD7wydypjUtoMSDRWvGSSfeLSVkTtEmfaI6m5M3skC4keaPUI9BGxtVhmZ5pA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityStoriesBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.item_stories_listview, Constants.NAMES_ARRAY);

        b.storiesListView.setAdapter(adapter);

        b.storiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick: " + i);
                b.currentText.setText(Constants.STORIES_ARRAY[i] + storiesSuffix);
                isShown = true;
                b.storiesListView.setVisibility(View.GONE);
                b.topLayoutBottomActivity.setVisibility(View.GONE);
                b.currentText.setVisibility(View.VISIBLE);
                b.currentTextScrollView.setVisibility(View.VISIBLE);
                b.backImg.setVisibility(View.VISIBLE);
            }
        });

    }

    private boolean isShown = false;

    @Override
    public void onBackPressed() {
        if (isShown) {
            isShown = false;
            b.storiesListView.setVisibility(View.VISIBLE);
            b.topLayoutBottomActivity.setVisibility(View.VISIBLE);
            b.currentText.setVisibility(View.GONE);
            b.currentTextScrollView.setVisibility(View.GONE);
            b.backImg.setVisibility(View.GONE);

        } else super.onBackPressed();
    }
}