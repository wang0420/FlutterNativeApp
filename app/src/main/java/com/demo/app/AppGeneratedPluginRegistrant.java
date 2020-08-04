package com.demo.app;

import com.demo.app.channel.FlutterBasePlugin;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.plugins.shim.ShimPluginRegistry;
import io.flutter.plugin.common.PluginRegistry;

/**
 * 插件注册
 *
 * @author wangwei
 * @date 2020/5/29.
 */
@Keep
public final class AppGeneratedPluginRegistrant {
    public static void registerWith(@NonNull FlutterEngine flutterEngine) {
        ShimPluginRegistry shimPluginRegistry = new ShimPluginRegistry(flutterEngine);
        FlutterBasePlugin.registerWith(shimPluginRegistry.registrarFor("com.demo.app.channel.FlutterBasePlugin"));

        PluginRegistry.Registrar registrar = shimPluginRegistry.registrarFor(SimpleView.class.getCanonicalName());
        SampleViewFactory playerViewFactory = new SampleViewFactory(registrar.messenger());
        registrar.platformViewRegistry().registerViewFactory("SampleView", playerViewFactory);


    }
}
