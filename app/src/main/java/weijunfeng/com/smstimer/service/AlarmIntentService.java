package weijunfeng.com.smstimer.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import weijunfeng.com.smstimer.broadcast.AlarmReceiver;
import weijunfeng.com.smstimer.utils.SMSLog;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class AlarmIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "weijunfeng.com.smstimer.service.action.FOO";
    private static final String ACTION_ALARM = "weijunfeng.com.smstimer.service.action.ALARM";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "weijunfeng.com.smstimer.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "weijunfeng.com.smstimer.service.extra.PARAM2";

    public AlarmIntentService() {
        super("AlarmIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, AlarmIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionAlarm(Context context, String param1, String param2) {
        Intent intent = new Intent(context, AlarmIntentService.class);
        intent.setAction(ACTION_ALARM);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            SMSLog.i(intent.getAction());
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_ALARM.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
//        // TODO: Handle action Baz
//        throw new UnsupportedOperationException("Not yet implemented");
        AlarmReceiver.startAlarm7_30(this.getApplicationContext());
        AlarmReceiver.startAlarm18_2(this.getApplicationContext());
        AlarmReceiver.startAlarm12(this.getApplicationContext());
        AlarmReceiver.startAlarm22(this.getApplicationContext());
    }
}
