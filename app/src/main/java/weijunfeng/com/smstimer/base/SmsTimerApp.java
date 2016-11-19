package weijunfeng.com.smstimer.base;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.squareup.leakcanary.LeakCanary;

import weijunfeng.com.smstimer.service.AlarmIntentService;

/**
 * Created by hexin on 2016/11/18.
 */

public class SmsTimerApp extends Application {
    public static volatile Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        context = this;
        CrashHandler.INSTANCE.init();
        enabledStrictMode();
        LeakCanary.install(this);
        AlarmIntentService.startActionAlarm(this, "7", "30");
        // Normal app init code...
    }

    private void enabledStrictMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                    .detectAll() //
                    .penaltyLog() //
                    .penaltyDeath() //
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder() //
                    .detectAll() //
                    .penaltyLog() //
                    .penaltyDeath() //
                    .build());
        }
    }
}
