package com.demo.app;

import io.flutter.embedding.engine.FlutterEngine;

/**
 * 请添加注释说明
 *
 * @author wangwei
 * @date 2020/5/29.
 */
public class FlutterEngineManage {


    private static FlutterEngine sInstance;

    public static FlutterEngine getsInstance() {
        if (sInstance == null) {
            synchronized (FlutterEngineManage.class) {
                sInstance = new FlutterEngine(BaseApplication.getInstance());
            }
        }
        return sInstance;
    }
}
