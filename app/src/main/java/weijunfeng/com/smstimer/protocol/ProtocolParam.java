package weijunfeng.com.smstimer.protocol;

/**
 * Created by hexin on 2016/11/19.
 */

public class ProtocolParam {
    public static final String DTYPE = "dtype";// JSON/XML
    public static final String KEY = "key";//申请的key
    public static final String CITYNAME = "CITYNAME";//城市名或城市id，需要urlencode
    public static final int format = 1; // 1或2
    public static final int count = 20; // 数量，最大为20
    public static final int cat = 1;// 类型， 1：表白，2：讨好，3：唠嗑，4：大爱，5：宠物，6：朋友阶段，7：打骂冤家
}
