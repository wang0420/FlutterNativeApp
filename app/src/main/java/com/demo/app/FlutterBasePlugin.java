package com.demo.app;

import android.content.Context;
import android.widget.Toast;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

/**
 * Created by wangwei on 2019/12/5.
 * 跳转Activity 插件
 */

public class FlutterBasePlugin implements FlutterPlugin, MethodChannel.MethodCallHandler {

    private Context context;
    private MethodChannel channel;

    public FlutterBasePlugin() {
    }


    public static void registerWith(PluginRegistry.Registrar registrar) {
        FlutterBasePlugin instance = new FlutterBasePlugin();
        instance.channel = new MethodChannel(registrar.messenger(), "base_flutter");
        instance.context = registrar.context();
        instance.channel.setMethodCallHandler(instance);

    }

    @Override
    public void onAttachedToEngine(FlutterPluginBinding binding) {
        channel = new MethodChannel(
                binding.getFlutterEngine().getDartExecutor(), "base_flutter");
        context = binding.getApplicationContext();
        channel.setMethodCallHandler(this);

    }

    @Override
    public void onDetachedFromEngine(FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
        channel = null;

    }


    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        switch (methodCall.method) {
            case "success":
                //解析参数
                String value = methodCall.argument("flutter");
                Toast.makeText(context, "" + value, Toast.LENGTH_SHORT).show();
                result.success("成功");
                break;
            default:
                result.notImplemented();
        }


    }
/*
    private static final String CHANNEL_NAME = "plugins.flutter/ulove_page_jump";

    @SuppressLint("StaticFieldLeak")
    private static Activity mActivity;

    private static int REQUEST_CODE_OPEN = 100;

    *//**
     * 暴露到外面的静态注册类
     *//*
    public static void registerWith(PluginRegistry registry) {
        PluginRegistry.Registrar registrar = registry.registrarFor("com.demo.app.FlutterBasePlugin");
        mActivity = registrar.activity();
        // 注册ActivityResult回调
        registrar.addActivityResultListener(new FlutterBasePlugin());
        MethodChannel channel = new MethodChannel(registrar.messenger(), CHANNEL_NAME);
        channel.setMethodCallHandler(new FlutterBasePlugin());

    }




    @Override
    public boolean onActivityResult(int i, int i1, Intent intent) {
        Toast.makeText(mActivity, "11111", Toast.LENGTH_SHORT).show();
        Log.w("TAG","onActivityResult 111111");

        return false;
    }*/
}
