package weijunfeng.com.smstimer.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Set;

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
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.text);
        loadData();
        listener = new Listener(this);
        SPUtil.setListener(listener);
    }

    private void loadData() {
        SharedPreferences sp = SPUtil.getSp();
        Map<String, String> all = (Map<String, String>) sp.getAll();
        if (all != null) {
            Set<Map.Entry<String, String>> entries = all.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                textView.append(entry.getKey() + "-----" + entry.getValue() + "\n");
            }
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
            MainActivityFragment mainActivityFragment = weakReference.get();
            if (mainActivityFragment == null || mainActivityFragment.isRemoving() || mainActivityFragment.isDetached()) {
                return;
            }
            mainActivityFragment.textView.append(key + "-----" + sharedPreferences.getString(key, "") + "\n");
        }
    }
}
