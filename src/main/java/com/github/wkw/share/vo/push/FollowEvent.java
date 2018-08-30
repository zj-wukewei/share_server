package com.github.wkw.share.vo.push;

/**
 * Created by GoGo on  2018/8/30
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class FollowEvent {
    private Integer userId;
    private String nickName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
