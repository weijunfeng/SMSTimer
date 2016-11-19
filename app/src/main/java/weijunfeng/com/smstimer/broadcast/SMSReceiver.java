package weijunfeng.com.smstimer.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import weijunfeng.com.smstimer.utils.SMSLog;
import weijunfeng.com.smstimer.utils.SPUtil;

public class SMSReceiver extends BroadcastReceiver {
    public static final String SENT_SMS_ACTION = "SENT_SMS_ACTION";
    public static final String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {
//        // TODO: This method is called when the BroadcastReceiver is receiving
//        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        SMSLog.i(intent.getAction());
        switch (intent.getAction()) {
            case SENT_SMS_ACTION:
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        SMSLog.i("短信发送成功:" + getResultCode());
//                        Toast.makeText(context,
//                                "短信发送成功", Toast.LENGTH_SHORT)
//                                .show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        break;
                }
                break;
            case DELIVERED_SMS_ACTION:
                SMSLog.i("收信人已经成功接收:" + getResultCode());
//                Calendar calendar = Calendar.getInstance();
//                calendar.get(Calendar.YEAR);
//                calendar.get(Calendar.MONTH);
//                calendar.get(Calendar.DAY_OF_MONTH);
                SPUtil.setStringValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), "收信成功");
                break;
            default:
                break;
        }
    }
}
