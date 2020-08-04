package com.demo.app;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.demo.app.channel.FlutterEventChannel;

import io.flutter.embedding.android.FlutterActivity;
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
public class MyFlutterActivity extends FlutterActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("TAG", "MyFlutterActivity");


    }

    @Override
    public void configureFlutterEngine(FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        Log.w("TAG", "-flutterEngine-->" + flutterEngine.hashCode());
        GeneratedPluginRegistrant.registerWith(flutterEngine);
        AppGeneratedPluginRegistrant.registerWith(flutterEngine);
        FlutterEventChannel.create(flutterEngine);
        testBasicMessageChannel(flutterEngine);
    }


    //--BasicMessageChannel 主要是传递字符串json等数据和一些半结构体的数据，需要指定编码方式--
    private void testBasicMessageChannel(FlutterEngine flutterEngine) {
        //BasicMessageChannel
        // 消息接收监听BasicMessageChannel（主要是传递字符串和一些半结构体的数据）
        //创建通道
        BasicMessageChannel<String> mMessageChannel = new BasicMessageChannel<String>(flutterEngine.getDartExecutor().getBinaryMessenger(), "BasicMessageChannelTest", StringCodec.INSTANCE);
        // 接收消息监听
        mMessageChannel.setMessageHandler((s, reply) -> {
            Toast.makeText(MyFlutterActivity.this, s, Toast.LENGTH_LONG).show();
            reply.reply("Native接受到flutter发送的信息并回复给他一个s");

        });


        /**
         * 向 Flutter 中发送消息
         * 参数 二可以再次接收到 Flutter 中的回调
         * 也可以直接使用 mMessageChannel.send(resultMap）
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMessageChannel.send("原生延迟3秒BasicMessageChannel发送的信息", new BasicMessageChannel.Reply<String>() {
                    @Override
                    public void reply(String s) {
                        Toast.makeText(MyFlutterActivity.this, s, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }, 5000);
    }

    public static NewMyEngineIntentBuilder withNewEngine(Class<? extends FlutterActivity> activityClass) {
        return new NewMyEngineIntentBuilder(activityClass);
    }


    //重写创建引擎方法
    public static class NewMyEngineIntentBuilder extends NewEngineIntentBuilder {

        protected NewMyEngineIntentBuilder(Class<? extends FlutterActivity> activityClass) {
            super(activityClass);
        }
    }


}
