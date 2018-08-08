package com.github.wkw.share.thirdparty.page;

public class AbstractQry {
    public static final int DEFAULT_PAGENUM = 0;
    public static final int DEFAULT_PAGESIZE = 20;
    public static final boolean DEFAULT_REQUIRETOTALCOUNT = false;

    private Integer pageNum = DEFAULT_PAGENUM;// 第几页，首页为1
    private Integer pageSize = DEFAULT_PAGESIZE;// 每页记录条数

    private Boolean requireTotalCount = Boolean.TRUE;// 是否需要记录总数
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getRequireTotalCount() {
        return requireTotalCount;
    }

    public void setRequireTotalCount(Boolean requireTotalCount) {
        this.requireTotalCount = requireTotalCount;
    }
}
