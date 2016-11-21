package weijunfeng.com.smstimer.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;

import java.util.ArrayList;

import weijunfeng.com.smstimer.base.SmsTimerApp;
import weijunfeng.com.smstimer.broadcast.SMSReceiver;

/**
 * Created by hexin on 2016/11/18.
 */

public class SmsUtil {
    public static void doSendSMSTo(Context context, String phoneNumber, String message) {
        if (PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
            intent.putExtra("sms_body", message);
            IntentUtil.startActivity(context, intent);
        }
    }

    public static void sendSMS(String phoneNumber, String message) {

        //获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        //拆分短信内容（手机短信长度限制）
        ArrayList<String> divideContents = smsManager.divideMessage(message);
//        for (String text : divideContents) {
//            smsManager.sendTextMessage(phoneNumber, null, text, getSendPi(SmsTimerApp.context), getDeliverPi(SmsTimerApp.context));
//        }
        ArrayList<PendingIntent> sendPi = new ArrayList<>();
        ArrayList<PendingIntent> deliverPi = new ArrayList<>();
        for (String text : divideContents) {
            sendPi.add(getSendPi(SmsTimerApp.context));
            deliverPi.add(getDeliverPi(SmsTimerApp.context));
        }
        /**
         * destinationAddress：目标电话号码
         -- scAddress：短信中心号码，测试可以不填
         -- text: 短信内容
         -- sentIntent：发送 -->中国移动 --> 中国移动发送失败 --> 返回发送成功或失败信号 --> 后续处理   即，这个意图包装了短信发送状态的信息
         -- deliveryIntent： 发送 -->中国移动 --> 中国移动发送成功 --> 返回对方是否收到这个信息 --> 后续处理  即：这个意图包装了短信是否被对方收到的状态信息（供应商已经发送成功，但是对方没有收到）。
         */
        smsManager.sendMultipartTextMessage(phoneNumber, null, divideContents, sendPi, deliverPi);
    }

    private static PendingIntent getSendPi(final Context context) {
        //处理返回的发送状态
        Intent sentIntent = new Intent(context, SMSReceiver.class);
        sentIntent.setAction(SMSReceiver.SENT_SMS_ACTION);
        PendingIntent sentPI = PendingIntent.getBroadcast(context, NotificationId.getId(), sentIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
//        String SENT_SMS_ACTION = "SENT_SMS_ACTION";
//        Intent sentIntent = new Intent(SENT_SMS_ACTION);
//        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, sentIntent,
//                0);
// register the Broadcast Receivers
//        context.registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                SMSLog.i("短信发送成功:" + getResultCode());
//                switch (getResultCode()) {
//                    case Activity.RESULT_OK:
//                        Toast.makeText(context,
//                                "短信发送成功", Toast.LENGTH_SHORT)
//                                .show();
//                        break;
//                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
//                        break;
//                    case SmsManager.RESULT_ERROR_RADIO_OFF:
//                        break;
//                    case SmsManager.RESULT_ERROR_NULL_PDU:
//                        break;
//                }
//            }
//        }, new IntentFilter(SENT_SMS_ACTION));
        return sentPI;
    }

    private static PendingIntent getDeliverPi(final Context context) {
        //处理返回的接收状态
// create the deilverIntent parameter
        Intent deliverIntent = new Intent(context, SMSReceiver.class);
        deliverIntent.setAction(SMSReceiver.DELIVERED_SMS_ACTION);
        PendingIntent deliverPI = PendingIntent.getBroadcast(context, NotificationId.getId(),
                deliverIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";
//// create the deilverIntent parameter
//        Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);
//        PendingIntent deliverPI = PendingIntent.getBroadcast(context, 0,
//                deliverIntent, 0);
//        context.registerReceiver(new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                SMSLog.i("收信人已经成功接收:" + getResultCode());
//                Toast.makeText(context,
//                        "收信人已经成功接收", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        }, new IntentFilter(DELIVERED_SMS_ACTION));
        return deliverPI;
    }
}
