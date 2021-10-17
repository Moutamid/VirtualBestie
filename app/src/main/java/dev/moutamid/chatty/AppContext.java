package dev.moutamid.chatty;

import android.app.Application;

import dev.moutamid.chatty.utilities.Utils;

public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
