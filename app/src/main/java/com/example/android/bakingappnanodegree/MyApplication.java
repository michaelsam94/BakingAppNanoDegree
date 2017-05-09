package com.example.android.bakingappnanodegree;

import android.app.Application;
import android.content.Context;

/**
 * Created by micky on 07-May-17.
 */

public class MyApplication extends Application {


    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getAppContext(){
        return mContext;
    }
}
