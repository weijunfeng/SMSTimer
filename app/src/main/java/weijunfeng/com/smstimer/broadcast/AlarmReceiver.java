package weijunfeng.com.smstimer.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import java.util.Calendar;

import weijunfeng.com.smstimer.model.SMSEnum;
import weijunfeng.com.smstimer.utils.NotificationId;
import weijunfeng.com.smstimer.utils.SMSLog;
import weijunfeng.com.smstimer.utils.SPUtil;
import weijunfeng.com.smstimer.utils.SmsUtil;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String ACTION_7 = "weijunfeng.com.smstimer_7";
    public static final String ACTION_12 = "weijunfeng.com.smstimer_12";
    public static final String ACTION_18 = "weijunfeng.com.smstimer_18";
    public static final String ACTION_22 = "weijunfeng.com.smstimer_22";
    public static final String ACTION_TEST = "weijunfeng.com.smstimer_test";
    public static final long INTERVALMILLIS = 24 * 60 * 60 * 1000;

    public static void startAlarmTest(Context context) {
        startAlarm(context, ACTION_TEST, getTriggerAtMillis(15, 27, 0));
    }
    public static void startAlarm7_30(Context context) {
        startAlarm(context, ACTION_7, getTriggerAtMillis(6, 50, 0));
    }

    public static void startAlarm12(Context context) {
//        startAlarm(context, ACTION_12, getTriggerAtMillis(11, 50, 0));
    }

    public static void startAlarm18(Context context) {
//        startAlarm(context, ACTION_18, getTriggerAtMillis(17, 50, 0));
    }

    public static void startAlarm18_2(Context context) {
//        Calendar calendar = Calendar.getInstance();
//        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
//        if (weekDay == Calendar.SUNDAY || weekDay == Calendar.SATURDAY) {
//            cancelAlarm(context, ACTION_18);
//        } else {
//            startAlarm18(context);
//        }
    }

    public static void startAlarm22(Context context) {
        startAlarm(context, ACTION_22, getTriggerAtMillis(22, 0, 0));
    }

    public static void cancelAlarm(Context context, String action) {
        PendingIntent pendingIntent = getPendingIntent(context, action);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    public static void startAlarm(Context context, String action, long triggerAtMillis) {
        PendingIntent pendingIntent = getPendingIntent(context, action);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, INTERVALMILLIS, pendingIntent);
    }

    private static PendingIntent getPendingIntent(Context context, String action) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, NotificationId.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static long getTriggerAtMillis(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        long currMillis = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        if (calendar.getTimeInMillis() < currMillis) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return calendar.getTimeInMillis();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        // TODO: This method is called when the BroadcastReceiver is receiving
//        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        SMSLog.i(intent.getAction());
        if (SPUtil.getStringValue(SPUtil.ALARM_ACTION).equals(intent.getAction())) {
            return;
        }
        SPUtil.setStringValue(SPUtil.ALARM_ACTION, intent.getAction());
        switch (intent.getAction()) {
            case ACTION_7:
                SmsUtil.sendSMS(SMSEnum.ALARM7.getPhoneNum(), SMSEnum.ALARM7.getContent(context));
                startAlarm18_2(context);
                break;
            case ACTION_12:
                SmsUtil.sendSMS(SMSEnum.ALARM12.getPhoneNum(), SMSEnum.ALARM12.getContent(context));
                break;
            case ACTION_18:
                SmsUtil.sendSMS(SMSEnum.ALARM18.getPhoneNum(), SMSEnum.ALARM18.getContent(context));
                break;
            case ACTION_22:
                SmsUtil.sendSMS(SMSEnum.ALARM22.getPhoneNum(), SMSEnum.ALARM22.getContent(context));
                break;
            case ACTION_TEST:
                SmsUtil.sendSMS(SMSEnum.ALARMTEST.getPhoneNum(), SMSEnum.ALARMTEST.getContent(context));
                break;
            default:
                break;
        }
    }
}
