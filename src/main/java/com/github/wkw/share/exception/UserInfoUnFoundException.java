package com.github.wkw.share.exception;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class UserInfoUnFoundException extends Exception {
    public UserInfoUnFoundException() {
        super();
    }

    public UserInfoUnFoundException(String message) {
        super(message);
    }

    public UserInfoUnFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInfoUnFoundException(Throwable cause) {
        super(cause);
    }
}
