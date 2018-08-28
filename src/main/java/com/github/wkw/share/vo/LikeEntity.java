package com.github.wkw.share.vo;

/**
 * Created by GoGo on  2018/8/28
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class LikeEntity {

    private boolean liked;
    private Integer lickCount;

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public Integer getLickCount() {
        return lickCount;
    }

    public void setLickCount(Integer lickCount) {
        this.lickCount = lickCount;
    }
}
