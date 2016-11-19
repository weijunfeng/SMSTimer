package weijunfeng.com.smstimer.base;

import weijunfeng.com.smstimer.utils.SMSLog;

/**
 * Created by hexin on 2016/11/18.
 */

public enum CrashHandler implements Thread.UncaughtExceptionHandler {
    INSTANCE;

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        SMSLog.e(ex.getMessage());
    }

    public void init() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
}
