package com.github.wkw.share.utils;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class StringUtils {
    public static boolean equals(String a, String b) {
        return a == b || (a != null && b != null && a.length() == b.length() && a.equals(b));
    }

    public static boolean isNotBlank(String str) {
        return str != null && str.length() > 0 && str.trim().length() > 0;
    }
}
