package weijunfeng.com.smstimer.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import weijunfeng.com.smstimer.utils.SMSLog;
import weijunfeng.com.smstimer.service.AlarmIntentService;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
//        // TODO: This method is called when the BroadcastReceiver is receiving
//        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        SMSLog.i("boot ok");
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            AlarmIntentService.startActionAlarm(context, "7", "30");
        }
    }
}
