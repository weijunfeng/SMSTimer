package weijunfeng.com.smstimer.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;

/**
 * Created by hexin on 2016/11/19.
 */

public class LoveBean {
    @JSONField(name = "data")
    private ArrayList<Item> data;

    public static class Item {
        @JSONField(name = "id")
        private String id;
        @JSONField(name = "valid")
        private String valid;
        @JSONField(name = "body")
        private String body;
        @JSONField(name = "title")
        private String title;
    }
}
