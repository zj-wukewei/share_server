package com.github.wkw.share.vo;

import com.github.wkw.share.Constants;

/**
 * Created by GoGo on  2018/8/2
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class ShareResponse<T> {

    private int code;
    private String msg;
    private T data;

    private ShareResponse(T data, int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ShareResponse(T data) {
        this(data, Constants.STATUS_CODE.SUCCESS, "success");
    }

    private ShareResponse(int code, String msg) {
        this(null, code, msg);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ShareResponse<T> ok(T data) {
        return new ShareResponse<T>(data);
    }

    public static <T> ShareResponse<T> fail(int code, String msg) {
        return new ShareResponse<T>(code, msg);
    }

    public static <T> ShareResponse<T> fail(String msg) {
        return new ShareResponse<T>(Constants.STATUS_CODE.ERROR_COMMON, msg);
    }
    public static <T> ShareResponse<T> fail(int code) {
        return new ShareResponse<T>(code, null);
    }
}
