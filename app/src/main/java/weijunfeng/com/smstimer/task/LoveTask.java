package weijunfeng.com.smstimer.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import okhttp3.Response;
import weijunfeng.com.smstimer.model.LoveBean;
import weijunfeng.com.smstimer.protocol.ProtocolKey;
import weijunfeng.com.smstimer.utils.OkHttpUtil;

/**
 * Created by hexin on 2016/11/19.
 */

public class LoveTask {
    public static LoveBean getLove() {
        String url = "http://japi.juhe.cn/love/list.from?key=" + ProtocolKey.LOVE_KEY + "&count=10&cat=1";
        try {
            Response response = OkHttpUtil.doGet(url, null);
            if (response.isSuccessful()) {
                String string = response.body().string();
                JSONObject jsonObject = JSON.parseObject(string);
                if (0 == jsonObject.getIntValue("error_code")) {
                    String result = jsonObject.getString("result");
                    LoveBean loveBean = JSON.parseObject(result, LoveBean.class);
                    return loveBean;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
