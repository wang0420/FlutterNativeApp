package com.demo.app;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.plugins.shim.ShimPluginRegistry;

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
        com.demo.app.FlutterBasePlugin.registerWith(shimPluginRegistry.registrarFor("com.demo.app.FlutterBasePlugin"));
    }
}
