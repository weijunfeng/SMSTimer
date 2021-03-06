package weijunfeng.com.smstimer.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import weijunfeng.com.smstimer.R;
import weijunfeng.com.smstimer.utils.SPUtil;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private TextView textView;
    private Listener listener;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.text);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        ((TextView) view.findViewById(R.id.boot_time)).setText("启动时间:" + simpleDateFormat.format(new Date()));
        loadData();
        listener = new Listener(this);
        SPUtil.setListener(listener);
//        SMSLog.d(ProperTask.getLoveError(getActivity()));
//        SMSLog.d(ProperTask.getLoveOk(getActivity()));
//        SmsUtil.sendSMS("17006429278", "测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
//        SmsUtil.sendSMS("17006429278", "测试");
    }

    private void loadData() {
        SharedPreferences sp = SPUtil.getSp();
        Map<String, String> all = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });
        all.putAll((Map<? extends String, ? extends String>) sp.getAll());
        Set<Map.Entry<String, String>> entries = all.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            if (entry.getKey().equals(SPUtil.ALARM_ACTION)) {
                continue;
            }
            textView.append(entry.getKey() + "-----" + entry.getValue() + "\n");
        }
    }

    @Override
    public void onDestroyView() {
        SPUtil.removeListener(listener);
        super.onDestroyView();
    }

    private static class Listener implements SharedPreferences.OnSharedPreferenceChangeListener {
        private WeakReference<MainActivityFragment> weakReference;

        public Listener(MainActivityFragment mainActivityFragment) {
            weakReference = new WeakReference<MainActivityFragment>(mainActivityFragment);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals(SPUtil.ALARM_ACTION)) {
                return;
            }
            MainActivityFragment mainActivityFragment = weakReference.get();
            if (mainActivityFragment == null || mainActivityFragment.isRemoving() || mainActivityFragment.isDetached()) {
                return;
            }
            mainActivityFragment.textView.append(key + "-----" + sharedPreferences.getString(key, "") + "\n");
        }
    }
}
