package com.github.wkw.share.vo;

/**
 * Created by GoGo on  2018/8/2
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class UserEntity {
    private Integer uId;
    private String token;

    public UserEntity(Integer uId, String token) {
        this.uId = uId;
        this.token = token;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
