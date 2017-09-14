package com.example.wj.baseproject.util;

import android.text.TextUtils;

import com.example.wj.baseproject.R;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符的工具类
 *
 * @author renyangyang
 */
public class StringUtil {

    private StringUtil() {
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 6-20位英文字符、下划线、数字组合
     *
     * @param password 要检查的密码字符串
     *
     * @return 是否符合要求
     */
    public static boolean checkPwd(String password) {

        boolean tag = true;
        String pattern1 = "^\\w{6,20}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(password);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

    public static String byteToM(long num) {
        double m = (double) num / 1024 / 1024;
        return String.format("%.2f", m);
    }

    /**
     * double 类型保留一位小数
     */
    public static double changeDouble(Double dou) {
        NumberFormat nf = new DecimalFormat("0.0 ");
        dou = Double.parseDouble(nf.format(dou));
        return dou;
    }

    /**
     * 校验是否是手机号码
     *
     * @param phone 手机号码
     *
     * @return 是否符合要求
     */
    public static boolean checkPhone(String phone) {
        boolean tag = true;
        String pattern1 = "^[1][4,3,5,7,8]+\\d{9}";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(phone);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

    /**
     * 将json字符串格式化后返回
     *
     * @param json json字符串
     *
     * @return 格式化后的字符串
     */
    public static String jsonFormat(String json) {
        if (TextUtils.isEmpty(json)) {
            return "Empty/Null json content";
        }
        try {
            json = json.trim();
            String message;
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                message = jsonObject.toString(2);
                return message;
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                message = jsonArray.toString(2);
                return message;
            } else {
                message = "Invalid Json";
            }
            return message;
        } catch (JSONException e) {
            Logger.e(e.getMessage());
            return "Invalid Json";
        }
    }

    /**
     * 将大的数字转换成中文数字
     *
     * @param num 要转换的数字
     *
     * @return 转换后的字符串
     */
    public static String bigInteger2String(int num) {

        float temp = (float) num / 10000f;
        if (temp < 1) {
            return String.valueOf(num);
        } else {
            DecimalFormat df = new DecimalFormat("0.0");
            return df.format(temp) + AppManager.getInstance().getFirstActivity()
                    .getString(R.string.ten_thousand);
        }
    }

    /**
     * 判断文本中是否包含Emoji表情
     *
     * @param source 文本
     *
     * @return 是否包含
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     *
     * @return 是否是Emoji
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return codePoint == 0x0 || codePoint == 0x9 || codePoint == 0xA
                || codePoint == 0xD || codePoint >= 0x20 && codePoint <= 0xD7FF
                || codePoint >= 0xE000 && codePoint <= 0xFFFD;
    }

    public static String int2Wan(int num) {
        float temp = (float) num / 10000f;
        if (temp < 1) {
            return num + "";
        } else {
            DecimalFormat df = new DecimalFormat("0.0");
            return df.format(temp).concat(AppManager.getInstance()
                    .getFirstActivity().getString(R.string.ten_thousand));
        }
    }
}
