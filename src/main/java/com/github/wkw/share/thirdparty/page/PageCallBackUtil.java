package com.github.wkw.share.thirdparty.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.wkw.share.utils.guava.Preconditions;
import com.github.wkw.share.vo.ListDataEntity;

import java.util.List;

public class PageCallBackUtil {

    public static <T> ListDataEntity<T> selectRtnPage(AbstractQry qry, IPageHelperPageCallBack<T> callBack) {

        Preconditions.checkArgument(qry != null, "qry 不能为 null");
        Preconditions.checkArgument(callBack != null, "callBack 不能为 null");
        setPageHelperStartPage(qry);
        List<T> list = callBack.select();
        if (!(list instanceof Page)) {
            throw new RuntimeException("list must be 'com.github.pagehelper.Page', now is " + list.getClass().getCanonicalName());
        }
        ListDataEntity<T> listData = new ListDataEntity<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        listData.setList(pageInfo.getList());
        listData.setTotal((int) pageInfo.getTotal());
        listData.setHasMore(pageInfo.isHasNextPage());
        return listData;
    }

    /**
     * 设置PageHelper的startPage
     *
     * @param qry
     */
    private static void setPageHelperStartPage(AbstractQry qry) {
        // 设置分页信息
        // pageNum
        Integer pageNum = qry.getPageNum();
        pageNum = pageNum == null ? AbstractQry.DEFAULT_PAGENUM : pageNum;
        // pageSize
        Integer pageSize = qry.getPageSize();
        pageSize = pageSize == null ? AbstractQry.DEFAULT_PAGESIZE : pageSize;
        // requireTotalCount
        Boolean requireTotalCount = qry.getRequireTotalCount();
        requireTotalCount = requireTotalCount == null ? AbstractQry.DEFAULT_REQUIRETOTALCOUNT : requireTotalCount;
        PageHelper.startPage(pageNum, pageSize, requireTotalCount);
    }
}
