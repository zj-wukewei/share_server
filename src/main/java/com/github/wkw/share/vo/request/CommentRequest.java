package com.github.wkw.share.vo.request;

import javax.validation.constraints.NotNull;

/**
 * Created by GoGo on  2018/9/3
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class CommentRequest {
    @NotNull(message = "feedId 不能为空")
    private Integer feedId;

    private Integer toUid;

    //null为主评论， 其他的为母评论的id
    private Integer tId;

    @NotNull(message = "content 不能为空")
    private String content;

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public Integer getToUid() {
        return toUid;
    }

    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
