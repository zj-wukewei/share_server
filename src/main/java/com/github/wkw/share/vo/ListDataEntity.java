package com.github.wkw.share.vo;

import java.util.Collections;
import java.util.List;

public class ListDataEntity<T> {
    private boolean hasMore;
    private int total;
    private List<T> list = Collections.emptyList();


    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public <R> ListDataEntity<R> transformBuildListEntity(List<R> entity) {
        ListDataEntity<R> transform = new ListDataEntity<>();
        transform.setHasMore(this.isHasMore());
        transform.setTotal(this.getTotal());
        transform.setList(entity);
        return transform;
    }
}
