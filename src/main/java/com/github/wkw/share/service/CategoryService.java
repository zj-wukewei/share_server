package com.github.wkw.share.service;

import com.github.wkw.share.domain.ShareCategory;
import com.github.wkw.share.thirdparty.page.AbstractQry;
import com.github.wkw.share.vo.ListDataEntity;
import com.github.wkw.share.vo.request.CategoryRequest;

import java.util.List;

/**
 * Created by GoGo on  2018/9/11
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public interface CategoryService {
    List<ShareCategory> lists();
    Integer addCategory(CategoryRequest request);
    ListDataEntity<ShareCategory> lists(AbstractQry qry);
}
