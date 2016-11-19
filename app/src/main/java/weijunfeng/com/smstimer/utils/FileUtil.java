package weijunfeng.com.smstimer.utils;

import android.content.Context;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by hexin on 2016/11/19.
 */

public class FileUtil {
    public static String getProper(String fileName, Context context, String key) {
        Properties proper = getProper(fileName, context);
        return proper.getProperty(key, "");
    }

    public static Properties getProper(String fileName, Context context) {
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(context.getAssets().open(fileName), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
