package com.zhou.reader.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.zhou.reader.App;

public class SafeToast extends Toast {

    public SafeToast(Context context) {
        super(context);
    }

    public void show() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.show();
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    SafeToast.super.show();
                }
            });
        }
    }

    public static Toast makeText(Context context, CharSequence text, int duration) {
        Toast origToast = Toast.makeText(context, text, duration);
        SafeToast safeToast = new SafeToast(context);
        safeToast.setView(origToast.getView());
        safeToast.setDuration(origToast.getDuration());
        return safeToast;
    }

    public static Toast makeText(CharSequence text) {
        return makeText(App.getAppContext(), text, Toast.LENGTH_SHORT);
    }

    public static Toast makeText(@StringRes int resId) {
        return makeText(App.getAppContext(), resId, Toast.LENGTH_SHORT);
    }

    public static Toast makeText(Context context, int resId, int duration) throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId), duration);
    }
}