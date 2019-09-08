package com.github.wkw.share;

/**
 * Created by GoGo on  2018/8/3
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class Constants {
    public static final String APP_NAME = "Share";
    //日期格式：yyyy-MM-dd HH:mm:ss
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    //日期格式：yyyy-MM-dd
    public static final String FORMAT_DATE = "yyyy-MM-dd";

    public static class STATUS_CODE {
        public static final int SUCCESS = 0;
        public static final int ERROR_COMMON = -1;
        //重新登录
        public static final int ERROR_RE_LOGIN = -1001;
        //去完成基本信息
        public static final int ERROR_PERFECT_PROFILE = -1010;
        //appid不合法
        public static final int ERROR_APP_ID_INVALID = -9990;
        //数据异常
        public static final int ERROR_DATA_DECODE = -9999;

        public static final int ERROR_NOT_EXISTED = -404;

        public static final int ERROR_UNKNOWN = -999;
        public static final int SESSION_TIME_OUT = -1011;
    }

    public static class APP_ID {
        public static final int IOS = 1;
        public static final int ANDROID = 2;
        public static final int WEB = 3;
    }

    public static class HTTP_HEADER {
        public static final String TOKEN = "TOKEN";

        public static final String APP_ID = "APP-ID";//1 ios 2 安卓
        public static final String APP_VERSION = "APP-VERSION";
        public static final String APP_MODEL = "APP-MODEL";
        public static final String NETWORK = "NETWORK";
        public static final String LANGUAGE = "Accept-Language";
    }

    public static class PageInfo {
        public static final Integer PAGE_SIZE = 20;
    }


    public static class FeedConstans {
        //社区
        public static final int TAG_COMMUNITY = 1;
        //内容
        public static final int TAG_CONTENT = 2;

        public static final int HOT_LIKE_COUNT = 100;

    }

}
