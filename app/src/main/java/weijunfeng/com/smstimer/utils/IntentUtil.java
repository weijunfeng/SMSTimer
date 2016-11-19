package weijunfeng.com.smstimer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by hexin on 2016/11/18.
 */

public class IntentUtil {
    public static boolean startActivity(Context context, Intent intent) {
        try {
            if (intent.resolveActivity(context.getPackageManager()) == null) {
                return false;
            }
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(intent, -1);
            } else {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
