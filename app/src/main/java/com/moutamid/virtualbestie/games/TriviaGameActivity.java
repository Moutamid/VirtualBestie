package com.moutamid.virtualbestie.games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moutamid.virtualbestie.R;
import com.moutamid.virtualbestie.databinding.ActivityTriviaGameBinding;
import com.moutamid.virtualbestie.models.Quiz;
import com.moutamid.virtualbestie.utilities.Utils;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class TriviaGameActivity extends AppCompatActivity {

    private ActivityTriviaGameBinding b;

    private ArrayList<Quiz> quizArrayList;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityTriviaGameBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        quizArrayList = new ArrayList<>(Utils.quizArrayList());


        SwipeStack swipeStack = (SwipeStack) findViewById(R.id.swipeStack);
        swipeStack.setAdapter(new SwipeStackAdapter(quizArrayList));

        swipeStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
                Utils.clickSound();

                // User said False

                if (quizArrayList.get(position).isAnswer()) {
                    // IF ANSWER IS TRUE
                    Utils.showOfflineDialog();
                } else {
                    Utils.showWorkDoneDialog();
                    counter++;
                }

            }

            @Override
            public void onViewSwipedToRight(int position) {
                Utils.clickSound();

                // User said TRUE

                if (quizArrayList.get(position).isAnswer()) {
                    // IF ANSWER IS TRUE
                    Utils.showWorkDoneDialog();
                    counter++;
                } else {
                    Utils.showOfflineDialog();
                }

            }

            @Override
            public void onStackEmpty() {

                Utils.showDialog("You got " + counter + " right out of " + quizArrayList.size() + ". Thanks for playing.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Utils.clickSound();

                        dialogInterface.dismiss();
                        finish();
                    }
                });

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.showDialog1();
            }
        }, 1000);


    }

    private class SwipeStackAdapter extends BaseAdapter {

        private List<Quiz> mData;

        public SwipeStackAdapter(List<Quiz> data) {
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position).getQuestion();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.card, parent, false);
            TextView textViewCard = (TextView) convertView.findViewById(R.id.textViewCard);
            textViewCard.setText(mData.get(position).getQuestion());

            return convertView;
        }
    }

}