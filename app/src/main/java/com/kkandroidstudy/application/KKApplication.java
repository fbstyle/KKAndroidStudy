package com.kkandroidstudy.application;

import android.app.Application;
import android.content.Context;

import com.kkandroidstudy.BuildConfig;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by shiyan on 2016/10/9.
 */
public class KKApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Logger
        Logger.init("KKAndroidStudy")
                .methodCount(3)// default 2
                .logLevel(LogLevel.FULL)//default LogLevel.FULL
                .methodOffset(0);//default 0

    }
}
