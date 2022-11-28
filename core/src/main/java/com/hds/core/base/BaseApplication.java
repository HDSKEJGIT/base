package com.hds.core.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static BaseApplication _instance;
    private static Activity _currentActivity = null;

    public static BaseApplication getInstance(){
        return _instance;
    }

    public static Context getContext(){
        return _instance;
    }

    public static Activity getCurrentActivity(){
        return _currentActivity;
    }

    public static Activity getActivity(Context context){
        Activity activity = null;
        if(context instanceof Activity){
            activity = (Activity)context;
        }else if(context instanceof ContextWrapper){
            activity = (Activity)(((ContextWrapper)context).getBaseContext());
        }
        return activity;
    }

    public ActivityInfo[] allActivities() throws PackageManager.NameNotFoundException {
        PackageManager packageManager = this.getPackageManager();
        PackageInfo info = packageManager.getPackageInfo(getApplicationContext().getPackageName(), PackageManager.GET_ACTIVITIES);
        ActivityInfo[] list = info.activities;
        return list;
    }

    @Override
    public void onCreate() {
        _instance = this;
        super.onCreate();
        registerActivityLifecycleCallbacks(this);

        BGASwipeBackHelper.init(this, null);


        // 置入一个不设防的VmPolicy：解决调用手机时的FileUriExposedException 问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        _currentActivity = activity;
    }

    @Override
    public void onActivityStarted(Activity activity) {
        _currentActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        _currentActivity = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
