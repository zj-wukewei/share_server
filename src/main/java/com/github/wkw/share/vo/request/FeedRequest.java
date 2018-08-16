package com.github.wkw.share.vo.request;

import com.github.wkw.share.thirdparty.page.AbstractQry;
import com.github.wkw.share.utils.validator.StringArray;

/**
 * Created by GoGo on  2018/8/15
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class FeedRequest extends AbstractQry {
    public static final String HOME = "2";
    public static final String HOT = "3";
    public static final String COMMUNITY = "1";

    //1：社区 2： 首页  3：热门
    @StringArray(value = {"1", "2", "3"}, message = "type只能为 \"1\"：社区 \"2\"： 首页  \"3\"：热门")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
