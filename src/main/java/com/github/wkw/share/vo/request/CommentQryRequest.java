package com.github.wkw.share.vo.request;

import com.github.wkw.share.thirdparty.page.AbstractQry;

import javax.validation.constraints.NotNull;

/**
 * Created by GoGo on  2018/8/15
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class CommentQryRequest extends AbstractQry {


    @NotNull(message = "feedId 不能为空")
    private Integer feedId;

    public Integer getFeedId() {
        return feedId;
    }

    private boolean isCommunity;

    public boolean isCommunity() {
        return isCommunity;
    }

    public void setCommunity(boolean community) {
        isCommunity = community;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }
}
