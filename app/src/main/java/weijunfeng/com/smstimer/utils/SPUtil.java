package weijunfeng.com.smstimer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import weijunfeng.com.smstimer.base.SmsTimerApp;

/**
 * Created by hexin on 2016/11/18.
 */

public class SPUtil {
    public static final String SP_FILE_NAME = "sms_send";
    public static final String ALARM_ACTION = "alarm_action";

    public static SharedPreferences getSp() {
        return SmsTimerApp.context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor() {
        return getSp().edit();
    }

    public static void setListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getSp().registerOnSharedPreferenceChangeListener(listener);
    }

    public static void removeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getSp().unregisterOnSharedPreferenceChangeListener(listener);
    }

    public static void setStringValue(String key, String value) {
        getEditor().putString(key, value).commit();
    }
    public static String getStringValue(String key) {
        return getSp().getString(key,"");
    }
}
