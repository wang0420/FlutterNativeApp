package com.demo.app;

import android.app.Application;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

/**
 * Created by wangwei on 2019/7/1.
 */

public class BaseApplication extends Application {

    private static BaseApplication sInstance;
    private String TAG = this.getClass().getSimpleName() + "-u";


    public static BaseApplication getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("Application has not been created");
        }
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (sInstance == null) {
            sInstance = this;
        }
        // 初始化FlutterEngine.
        FlutterEngine flutterEngine = FlutterEngineManage.getsInstance();
       // flutterEngine.getNavigationChannel().setInitialRoute("/screen1");
        // 使用dart executor来预热FlutterEngine.
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );
        // 缓存FlutterEngine，并且给一个id，方便后面获取
        FlutterEngineCache
                .getInstance()
                .put("my_engine_id", flutterEngine);

    }
}
