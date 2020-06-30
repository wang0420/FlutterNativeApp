package com.demo.app;

/**
 * 请添加注释说明
 *
 * @author wangwei
 * @date 2020/6/30.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformView;

class SimpleView implements PlatformView, MethodChannel.MethodCallHandler {
    private final MethodChannel methodChannel;
    private final View view;

    public SimpleView(Context context, int id, BinaryMessenger messenger) {
        view = LayoutInflater.from(context).inflate(R.layout.flutter_user_native_layout, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(Color.parseColor(ColorUtil.generateRandomColor()));
        methodChannel = new MethodChannel(messenger, "samples.wangwei/native_views_" + id);
        methodChannel.setMethodCallHandler(this);

    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if (methodCall.method.equals("changeBackgroundColor")) {
            view.setBackgroundColor(Color.parseColor(ColorUtil.generateRandomColor()));
            result.success(0);
        } else {
            result.notImplemented();
        }

    }


    @Override
    public View getView() {
        return view;
    }

    @Override
    public void dispose() {

    }
}