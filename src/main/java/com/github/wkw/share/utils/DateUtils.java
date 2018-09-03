package com.github.wkw.share.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by GoGo on  2018/9/3
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class DateUtils {
    public static String betweenTime(LocalDateTime startTime) {
        String timeString = "很久了";
        Duration duration = Duration.between(startTime, LocalDateTime.now());
        long m = duration.toMinutes();
        if (m <= 3) {
            timeString = "刚刚";
        } else if (m > 5 && m < 60) {
            timeString = m + "分钟前";
        } else if (m >= 60 && m < 60 * 24) {
            timeString = duration.toHours() + "小时前";
        } else if (m >= 60 * 24 && m < 60 * 24 * 2) {
            timeString = "1天前";
        } else if (m >= 60 * 24 * 2 && m < 60 * 24 * 3) {
            timeString = "2天前";
        } else if (m >= 60 * 24 * 3) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            timeString = df.format(startTime);
        }
        return timeString;
    }
}
