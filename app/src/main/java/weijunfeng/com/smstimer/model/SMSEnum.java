package weijunfeng.com.smstimer.model;

import android.content.Context;
import android.text.TextUtils;

import java.util.Calendar;

import weijunfeng.com.smstimer.task.ProperTask;

/**
 * Created by hexin on 2016/11/21.
 */

public enum SMSEnum {
    ALARMTEST("17006429278") {
        @Override
        public String getContent(Context context) {
            String message = "测试";
            if (TextUtils.isEmpty(customStr)) {
                return message;
            } else {
                message = customStr + ", " + message;
                customStr = "";
                return message;
            }
        }
    },
    ALARM7("15555481806") {
        @Override
        public String getContent(Context context) {
//            return ProperTask.getLoveshi(context) + " 小妹妹，早安。";
//            return ProperTask.getLoveError(context) + " 美好的一天从此刻开始,早安!";
            return "美好的一天从此刻开始,早安!";
        }
    },
    ALARM12("15555481806") {
        @Override
        public String getContent(Context context) {
            Calendar calendar = Calendar.getInstance();
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            String message;
            if (weekDay == Calendar.SUNDAY || weekDay == Calendar.SATURDAY) {
                message = ProperTask.getXiaoHua2(context) + " 小妹妹，该起床吃饭啦。";
            } else {
                message = ProperTask.getXiaoHua2(context) + " 小妹妹，记得多吃点。";
            }
            if (TextUtils.isEmpty(customStr)) {
                return message;
            } else {
                message = customStr + ", " + message;
                customStr = "";
                return message;
            }
        }
    },
    ALARM18("15555481806") {
        @Override
        public String getContent(Context context) {
            return ProperTask.getXiaoHua(context) + " 小妹妹，下班早点回去，注意安全。";
        }
    },
    ALARM22("15555481806") {
        @Override
        public String getContent(Context context) {
//            return ProperTask.getLoveError(context) + " 小妹妹，别玩太晚记得早点休息。";
            return "美好的夜从此刻到来,晚安!";
        }
    };

    public String customStr;
    private String phoneNum;

    SMSEnum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public abstract String getContent(Context context);

    public String getPhoneNum() {
        return phoneNum;
    }
}
