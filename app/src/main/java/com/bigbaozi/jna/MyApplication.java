package com.bigbaozi.jna;

import android.app.Application;
import android.support.multidex.MultiDex;

/**
 * Name: MyApplication
 * Author: dabaozi
 * Email:
 * Comment: //TODO
 * Date: 2017-11-09 16:23
 */
public class MyApplication   extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }


}
