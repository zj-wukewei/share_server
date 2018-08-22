package com.github.wkw.share.vo;

/**
 * Created by GoGo on  2018/8/21
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class Follow {
    private Integer id;

    private Integer userId;

    private String nickname;

    private String avatar;

    //是否关注
    private boolean followed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }
}
