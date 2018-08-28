package com.github.wkw.share.vo.push;

/**
 * Created by GoGo on  2018/8/28
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class LikeEvent {
    private Integer userId;
    private String nickName;
    private Integer feedId;
    private String content;

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

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
