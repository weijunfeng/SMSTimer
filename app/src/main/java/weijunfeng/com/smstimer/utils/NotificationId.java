package weijunfeng.com.smstimer.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hexin on 2016/11/18.
 */

public class NotificationId {
    private static AtomicInteger integer = new AtomicInteger(0);

    public static int getId() {
        return integer.incrementAndGet();
    }
}
