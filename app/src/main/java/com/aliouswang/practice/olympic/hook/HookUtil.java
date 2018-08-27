package com.aliouswang.practice.olympic.hook;

import android.app.Activity;
import android.app.Instrumentation;

import java.lang.reflect.Field;

/**
 * Created by aliouswang on 2018/8/27.
 */

public class HookUtil {

    public static void hook(Activity activity) {
        try {
            Field instrumentationField = Activity.class.getDeclaredField("mInstrumentation");
            instrumentationField.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) instrumentationField.get(activity);
            HookInstrumention hookInstrumention = new HookInstrumention(instrumentation);
            instrumentationField.set(activity, hookInstrumention);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
