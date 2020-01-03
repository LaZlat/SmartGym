package com.example.freesmartgym;

import android.app.Application;
import android.content.Context;

public final class FreeSmartGym extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        FreeSmartGym.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return FreeSmartGym.context;
    }
}
