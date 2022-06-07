package com.demo.cloud.common.core.util;

/**
 * @author LiuYuQi
 * @version 1.0
 * @description: TODO
 * @date 2022/4/11 10:26
 */
public class CustomDateUtils {
    /**
     * 根据传入的分钟转化为对应的天数小时分钟 如 : 1天12小时23分
     *
     * @param minute 分钟
     * @return
     */
    public static String getNumTime(long minute) {
        String days = "";
        if (minute < 60) {
            days = minute + "分钟";
        } else if (minute < 1440) {
            long value = minute % 60;
            days = (minute - value) / 60 + "小时"
                    + value + "分钟";
        } else {
            long MinuteValue = minute % 60;
            long value = (minute - MinuteValue) / 60;
            long hourValue = value % 24;
            long dayValue = (value - hourValue) / 24;
            days = dayValue + "天" + hourValue + "小时"
                    + MinuteValue + "分钟";
        }
        return days;
    }
}
