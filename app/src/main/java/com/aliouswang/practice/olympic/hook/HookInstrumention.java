package com.aliouswang.practice.olympic.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.aliouswang.practice.olympic.util.L;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by aliouswang on 2018/8/27.
 */

public class HookInstrumention extends Instrumentation{

    private Instrumentation mTarget;

    public HookInstrumention(Instrumentation target) {
        this.mTarget = target;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                  Intent intent, int requestCode, Bundle options) {
        L.d("before start activity!");
        Class superClass = Instrumentation.class;
        try {
            Method method = superClass.getDeclaredMethod("execStartActivity",
                    Context.class, IBinder.class, IBinder.class, Activity.class,
                    Intent.class, int.class, Bundle.class);
            method.setAccessible(true);
            return (ActivityResult) method.invoke(this.mTarget, who, contextThread, token, target, intent, requestCode, options);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
