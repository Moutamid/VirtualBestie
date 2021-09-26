package com.moutamid.virtualbestie;

import android.app.Application;

import com.moutamid.virtualbestie.utilities.Utils;

public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
