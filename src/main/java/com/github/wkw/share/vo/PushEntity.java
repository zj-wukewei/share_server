package com.github.wkw.share.vo;

/**
 * Created by GoGo on  2018/8/28
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class PushEntity {
    private int type;
    private Object data;

    public PushEntity(int type, Object data) {
        this.type = type;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
