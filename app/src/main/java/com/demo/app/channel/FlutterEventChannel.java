package com.demo.app.channel;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.EventChannel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 请添加注释说明
 *
 * @author wangwei
 * @date 2020/5/29.
 */
public class FlutterEventChannel implements EventChannel.StreamHandler {

    private static final String TAG = "FlutterEventChannel";

    private FlutterEventChannel(FlutterEngine flutterEngine) {
        EventChannel eventChannel = new EventChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), "flutter_event_channel");
        eventChannel.setStreamHandler(this);
    }

    public static FlutterEventChannel create(FlutterEngine flutterEngine) {
        return new FlutterEventChannel(flutterEngine);
    }

    private EventChannel.EventSink eventSink;

    /**
     * 暴露出去供界面传数据到Flutter
     */
    public void sendEvent(Object data) {
        if (eventSink != null) {
            eventSink.success("数据流" + data);
        } else {
            Log.e(TAG, "===== FlutterEventChannel.eventSink 为空 需要检查一下 =====");
        }
    }

    int i = 0;

    @SuppressLint("CheckResult")
    @Override
    public void onListen(Object o, EventChannel.EventSink eventSink) {
        this.eventSink = eventSink;

        Observable.interval(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    Log.w(TAG, "" + aLong);
                    i++;
                    sendEvent(i);
                });

    }

    @Override
    public void onCancel(Object o) {
        eventSink = null;
    }


}