package com.moutamid.virtualbestie.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

public class Helper {

    public static TextWatcher notesEditTextWatcher(ImageView saveNotesBtn) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // THIS IS USED WHEN A USER TYPES SOMETHING IN THE NOTES THEN THE SAVE BUTTON APPEARS
                saveNotesBtn.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }
}
