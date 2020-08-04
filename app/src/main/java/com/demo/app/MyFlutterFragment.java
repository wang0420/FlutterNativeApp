package com.demo.app;

import android.os.Handler;
import android.util.Log;

import com.demo.app.channel.FlutterEventChannel;

import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.StringCodec;
import io.flutter.plugins.GeneratedPluginRegistrant;

/**
 * 请添加注释说明
 *
 * @author wangwei
 * @date 2020/5/29.
 */
public class MyFlutterFragment extends FlutterFragment {

    /*FlutterFragment显示过渡图片,和FlutterActivity一样，FlutterFragment在加载flutter时也支持增加过渡图片显示*/
   /* @Override
    public SplashScreen provideSplashScreen() {
        // Load the splash Drawable.
        Drawable splash = BaseApplication.getInstance().getResources().getDrawable(R.mipmap.pic1);

        // Construct a DrawableSplashScreen with the loaded splash Drawable and
        // return it.
        return new DrawableSplashScreen(splash);
    }*/


    @Override
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        Log.w("TAG","----MyFlutterFragment->");
        GeneratedPluginRegistrant.registerWith(flutterEngine);
        AppGeneratedPluginRegistrant.registerWith(flutterEngine);
        FlutterEventChannel.create(flutterEngine);
        //BasicMessageChannel
        // 消息接收监听BasicMessageChannel（主要是传递字符串和一些半结构体的数据）
        //创建通
        BasicMessageChannel<String> mMessageChannel = new BasicMessageChannel<String>(flutterEngine.getDartExecutor().getBinaryMessenger(), "flutter_and_native_100", StringCodec.INSTANCE);
        // 接收消息监听
        mMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler<String>() {
            @Override
            public void onMessage(String s, BasicMessageChannel.Reply<String> reply) {
                Log.w("---->", "Android接受到flutter发送的信息 =" + s);
                reply.reply("Reply from Android");

            }
        });


        /**
         * 向 Flutter 中发送消息
         * 参数 二可以再次接收到 Flutter 中的回调
         * 也可以直接使用 mMessageChannel.send(resultMap）
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMessageChannel.send("我是原生延迟3秒发送过来的信息", new BasicMessageChannel.Reply<String>() {
                    @Override
                    public void reply(String s) {
                        Log.w("---->", "flutter 收到Android主动发送的信息并回复给你一个A =" + s);

                    }
                });
            }
        }, 3000);
    }


}
