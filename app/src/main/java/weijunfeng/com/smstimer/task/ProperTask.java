package weijunfeng.com.smstimer.task;

import android.content.Context;

import java.util.Calendar;

import weijunfeng.com.smstimer.utils.FileUtil;
import weijunfeng.com.smstimer.utils.SMSLog;

/**
 * Created by hexin on 2016/11/19.
 */

public class ProperTask {
    public static final String LOVE_ERROR = "love_error_2016.properties";
    public static final String LOVE_Ok = "love_ok_2016.properties";
    public static final String LOVE_SHI = "love_shi_2016.properties";
    public static final String LOVE_XIAOHUA = "love_xiaohua_2016.properties";
    public static final int[] LOVE_ERROR_START = {2016, 11, 19};
    public static final int[] LOVE_OK_START = {2016, 11, 20};
    public static final int[] LOVE_SHI_START = {2016, 11, 20};
    public static final int[] LOVE_XIAOHUA_START = {2016, 11, 21};
    public static final String LOVE_XIAOHUA_2 = "love_xiaohua_2016_2.properties";
    public static final int[] LOVE_XIAOHUA2_START = {2016, 11, 23};


    public static String getLoveError(Context context) {
        int properKey = getProperKey(LOVE_ERROR_START);
        if (properKey <= -1) {
            return "";
        }
        return FileUtil.getProper(LOVE_ERROR, context, String.valueOf(properKey));
    }

    public static String getLoveOk(Context context) {
        int properKey = getProperKey(LOVE_OK_START);
        if (properKey <= -1) {
            return "";
        }
        return FileUtil.getProper(LOVE_Ok, context, String.valueOf(properKey));
    }

    public static String getLoveshi(Context context) {
        int properKey = getProperKey(LOVE_SHI_START);
        if (properKey <= -1) {
            return "";
        }
        return FileUtil.getProper(LOVE_SHI, context, String.valueOf(properKey));
    }

    public static String getXiaoHua(Context context) {
        int properKey = getProperKey(LOVE_XIAOHUA_START);
        if (properKey <= -1) {
            return "";
        }
        return FileUtil.getProper(LOVE_XIAOHUA, context, String.valueOf(properKey));
    }
    public static String getXiaoHua2(Context context) {
        int properKey = getProperKey(LOVE_XIAOHUA2_START);
        if (properKey <= -1) {
            return "";
        }
        return FileUtil.getProper(LOVE_XIAOHUA_2, context, String.valueOf(properKey));
    }

    private static int getProperKey(int[] start) {
        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        if (start[0] == currYear) {
            int currDay = calendar.get(Calendar.DAY_OF_YEAR);
            calendar.set(start[0], start[1] - 1, start[2]);
            int startDay = calendar.get(Calendar.DAY_OF_YEAR);
            SMSLog.d("startDay:" + startDay + " currDay:" + currDay);
            return currDay - startDay;
        }
        return -1;
    }
}
