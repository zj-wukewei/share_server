package com.github.wkw.share.vo.request;

import com.github.wkw.share.thirdparty.page.AbstractQry;

/**
 * Created by GoGo on  2018/8/15
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class FeedRequest extends AbstractQry {
    //1：社区 2： 首页  3：热门
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
