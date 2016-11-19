package weijunfeng.com.smstimer.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by hexin on 2016/11/19.
 */

public class BaseModel {
    @JSONField(name = "error_code")
    private String errorCode;
    @JSONField(name = "reason")
    private String reason;
}
