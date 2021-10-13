package com.moutamid.virtualbestie.activities;

import static com.moutamid.virtualbestie.utilities.Utils.getArrayList;
import static com.moutamid.virtualbestie.utilities.Utils.store;
import static com.moutamid.virtualbestie.utilities.Utils.toast;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.moutamid.virtualbestie.AppCompatLineEditText;
import com.moutamid.virtualbestie.R;
import com.moutamid.virtualbestie.helper.Helper;
import com.moutamid.virtualbestie.utilities.Constants;
import com.moutamid.virtualbestie.utilities.Utils;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    private ImageView saveNotesBtn;
    private AppCompatLineEditText editText;
    MaterialButton savedNotesBtn;

    ArrayList<String> tasksArrayList = getArrayList(Constants.NOTES_LIST);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        saveNotesBtn = findViewById(R.id.saveBtnNotes);
        savedNotesBtn = findViewById(R.id.savedNotesBtn);
        saveNotesBtn.setOnClickListener(saveNotesBtnClickListener());

        editText = findViewById(R.id.notesEdittext);
        editText.addTextChangedListener(Helper.notesEditTextWatcher(saveNotesBtn));

        initSavedNotesData();

    }

    private void initSavedNotesData() {
        if (!tasksArrayList.get(0).equals("Error")) {
            initRecyclerView();
            savedNotesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initRecyclerView();

                    if (editText.getVisibility() == View.VISIBLE) {
                        editText.setVisibility(View.GONE);
                        savedNotesBtn.setText("Hide Saved Notes");
                        conversationRecyclerView.setVisibility(View.VISIBLE);
                    } else {
                        editText.setVisibility(View.VISIBLE);
                        savedNotesBtn.setText("Show Saved Notes");
                        conversationRecyclerView.setVisibility(View.GONE);
                    }
                }
            });
        } else {
            savedNotesBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toast("List is Empty!");

                }
            });
        }
    }

    private View.OnClickListener saveNotesBtnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty()) {
                    toast("Please enter some text in the notes!");
                    return;
                }

                // THIS IS THE ARRAY LIST STORED IN THE SHARED PREFERENCES
                if (tasksArrayList.get(0).equals("Error")) {
                    ArrayList<String> notesArrayList = new ArrayList<>();
                    notesArrayList.add(editText.getText().toString());
                    store(Constants.NOTES_LIST, notesArrayList);
                } else {
                    tasksArrayList.add(editText.getText().toString());
                    store(Constants.NOTES_LIST, tasksArrayList);
                }
                editText.setText("");
                toast("Saved!");
                saveNotesBtn.setVisibility(View.GONE);

            }
        };
    }

//    private ArrayList<String> tasksArrayList = new ArrayList<>();

    private RecyclerView conversationRecyclerView;
    private RecyclerViewAdapterMessages adapter;

    private void initRecyclerView() {

        conversationRecyclerView = findViewById(R.id.notesListRecyclerView);
        //conversationRecyclerView.addItemDecoration(new DividerItemDecoration(conversationRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        adapter = new RecyclerViewAdapterMessages();
        //        LinearLayoutManager layoutManagerUserFriends = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        //    int numberOfColumns = 3;
        //int mNoOfColumns = calculateNoOfColumns(getApplicationContext(), 50);
        //  recyclerView.setLayoutManager(new GridLayoutManager(this, mNoOfColumns));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        conversationRecyclerView.setLayoutManager(linearLayoutManager);
        conversationRecyclerView.setHasFixedSize(true);
        conversationRecyclerView.setNestedScrollingEnabled(false);

        conversationRecyclerView.setAdapter(adapter);

        //    if (adapter.getItemCount() != 0) {

        //        noChatsLayout.setVisibility(View.GONE);
        //        chatsRecyclerView.setVisibility(View.VISIBLE);

        //    }

    }

    /*public static int calculateNoOfColumns(Context context, float columnWidthDp) { // For example columnWidthdp=180
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }*/

    private class RecyclerViewAdapterMessages extends RecyclerView.Adapter
            <RecyclerViewAdapterMessages.ViewHolderRightMessage> {

        @NonNull
        @Override
        public ViewHolderRightMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_notes, parent, false);
            return new ViewHolderRightMessage(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolderRightMessage holder, int position1) {

            int position = holder.getAdapterPosition();

            holder.title.setText(tasksArrayList.get(position));

            holder.title.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new AlertDialog.Builder(NotesActivity.this)
                            .setTitle("Are you sure?")
                            .setMessage("Do you really want to delete this note?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    tasksArrayList.remove(position);
                                    adapter.notifyItemRemoved(position);
                                    store(Constants.NOTES_LIST, tasksArrayList);

                                    dialogInterface.dismiss();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .show();

                    return false;
                }
            });

        }

        @Override
        public int getItemCount() {
            if (tasksArrayList == null)
                return 0;
            return tasksArrayList.size();
        }

        public class ViewHolderRightMessage extends RecyclerView.ViewHolder {

            TextView title;

            public ViewHolderRightMessage(@NonNull View v) {
                super(v);
                title = v.findViewById(R.id.titleTextview);

            }
        }

    }

}