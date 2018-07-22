package com.jwplayer.opensourcedemo;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

public class MyApplication extends MultiDexApplication {

    private static final String MEASURE_TAG = "JWMeasure";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(MEASURE_TAG, "" + Time.currentTime() + " MyApplication onCreate: start");
        MultiDex.install(this);
    }
}



