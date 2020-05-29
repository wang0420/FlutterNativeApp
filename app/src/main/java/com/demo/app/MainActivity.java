package com.demo.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import io.flutter.embedding.android.FlutterFragment;
import io.flutter.embedding.android.FlutterView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private LinearLayout one, two, three;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setListener();
    }


    private void findView() {
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);

        textView = findViewById(R.id.params);


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

    private void setListener() {
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

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NativeActivity.class));
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
}