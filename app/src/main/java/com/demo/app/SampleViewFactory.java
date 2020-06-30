package com.demo.app;

import android.content.Context;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

/**
 * 原生View
 *
 * @author wangwei
 * @date 2020/6/30.
 */
class SampleViewFactory extends PlatformViewFactory {
    private final BinaryMessenger messenger;

    public SampleViewFactory(BinaryMessenger msger) {
        super(StandardMessageCodec.INSTANCE);
        messenger = msger;
    }

    @Override
    public PlatformView create(Context context, int id, Object obj) {
        return new SimpleView(context, id, messenger);
    }
}
