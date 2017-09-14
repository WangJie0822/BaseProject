package com.example.wj.baseproject.util;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 */
public class AppManager {

    private Stack<AppCompatActivity> activityStack;

    private AppManager() {
    }

    private static class Helper {
        static final AppManager INSTANCE = new AppManager();
    }

    public static AppManager getInstance() {
        return Helper.INSTANCE;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(AppCompatActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);

        Logger.w("AppManager--->>" + activity.toString() + " size--->>" + activityStack.size());
    }

    /**
     * 将Activity从堆栈中移除
     */
    public void removeActivity(AppCompatActivity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定的Activity
     */
    private void finishActivity(AppCompatActivity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<? extends AppCompatActivity> cls) {

        AppCompatActivity delActivity = null;
        for (AppCompatActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                delActivity = activity;
            }
        }
        finishActivity(delActivity);
    }

    /**
     * 结束所有Activity
     */
    private void finishAllActivity() {

        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void appExit(Context context) {

        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            Logger.e(e, "APP_EXIT_ERROR");
        }
    }

    /**
     * 获取栈顶的Activity
     *
     * @return 栈顶的Activity
     */
    public AppCompatActivity getFirstActivity() {
        return activityStack.peek();
    }

    public <T extends AppCompatActivity> T getActivity(Class<T> cls) {
        T target = null;
        for (AppCompatActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                // noinspection unchecked
                target = (T) activity;
                break;
            }
        }
        return target;
    }
}