package com.example.wj.baseproject.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 应用相关工具类
 */
public class AppUtil {

    //    /**
    //     * 单例模式帮助类
    //     */
    //    private static final class Helper {
    //        static final AppUtil INSTANCE = new AppUtil();
    //    }
    //
    //    /**
    //     * 获取工具类实例对象
    //     *
    //     * @return 工具类实例对象
    //     */
    //    public static AppUtil getInstance() {
    //        return Helper.INSTANCE;
    //    }

    /**
     * 私有化构造
     */
    private AppUtil() {
    }

    /**
     * 根据路径获取Json字符串
     *
     * @param fileName 文件路径
     *
     * @return Json字符串
     */
    @NonNull
    public static String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 判断是否插入SD卡，SD卡是否可用
     */
    private static boolean hasSdcard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED) && Environment.getExternalStorageDirectory().canWrite();
    }

    /**
     * 得到外部存储总空间(MB)
     */
    @SuppressWarnings("deprecation")
    public static long getTotalExternalMemorySize() {
        if (hasSdcard()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } else {
            return -1;
        }
    }

    /**
     * 得到外部存储剩余空间(MB)
     */
    @SuppressWarnings("deprecation")
    public static long getAvailableExternalMemorySize() {
        if (hasSdcard()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return -1;
        }
    }

    @SuppressLint("HardwareIds")
    public static String getIMEI() {
        String imei;
        Context context = AppManager.getInstance().getFirstActivity();
        TelephonyManager telephonyManager =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        imei = telephonyManager.getDeviceId();

        if (TextUtils.isEmpty(imei)) {
            imei = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            if ("9774d56d682e549c".equals(imei)) {
                // 某些主流设备 android_id是固定的
                imei = "";
            }
        }
        if (TextUtils.isEmpty(imei)) {
            imei = "";
        }
        return imei;
    }
}
