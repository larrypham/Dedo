package com.akiniyalocts.dedo;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

/**
 * Created by anthony on 8/23/15.
 */
public abstract class BaseApplication extends Application {

    private static Context context;

    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }


}
