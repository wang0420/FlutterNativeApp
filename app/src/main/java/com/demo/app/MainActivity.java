package com.demo.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.android.FlutterView;

public class MainActivity extends AppCompatActivity {

    private Button one, two;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        setListener();
    }


    private void setListener() {
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MyFlutterActivity
                        .withNewEngine(MyFlutterActivity.class)
                        .initialRoute("/ChannelPage")
                        .build(MainActivity.this);
                startActivity(intent);
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动main 默认路由 /
                // startActivity(FlutterActivity.createDefaultIntent(MainActivity.this));
                //指定路由
       /*         startActivity(
                        FlutterActivity
                                .withNewEngine()
                                .initialRoute("/MyHomePage")
                                .build(MainActivity.this));
                //使用缓存的缓存的FlutterEngine：
               /* Intent intent = FlutterActivity
                        .withCachedEngine("my_engine_id")
                        .build(MainActivity.this);
                //主要加入这句话  全屏  .backgroundMode(FlutterActivity.BackgroundMode.transparent)//设置backgroundMode
                intent.putExtra("background_mode", "transparent");
                startActivity(intent);*/

                Intent intent = MyFlutterActivity
                        .withNewEngine(MyFlutterActivity.class)
                        .initialRoute("/MyHomePage")
                        .build(MainActivity.this);
                startActivity(intent);


            }
        });

    }


    private void addToFragment() {
        //fragment 加载
        FlutterFragment.NewEngineFragmentBuilder newEngineFragmentBuilder = new FlutterFragment.NewEngineFragmentBuilder(MyFlutterFragment.class);
        newEngineFragmentBuilder.initialRoute("/MyHomePage");
        newEngineFragmentBuilder.renderMode(FlutterView.RenderMode.texture);
        MyFlutterFragment myFlutterFragment = newEngineFragmentBuilder.build();


        MyFlutterFragment myFlutterFragment2 = FlutterFragment.withNewEngine()
                .initialRoute("/MyHomePage")
                .build();


        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.rl_flutter, myFlutterFragment);
        tx.commit();
    }

}