package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareCategoryMapper;
import com.github.wkw.share.domain.ShareCategory;
import com.github.wkw.share.service.CategoryService;
import com.github.wkw.share.thirdparty.page.AbstractQry;
import com.github.wkw.share.thirdparty.page.PageCallBackUtil;
import com.github.wkw.share.vo.ListDataEntity;
import com.github.wkw.share.vo.request.CategoryRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by GoGo on  2018/9/11
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    ShareCategoryMapper shareCategoryMapper;

    @Override
    public List<ShareCategory> lists() {
        return shareCategoryMapper.selectByExample(null);
    }

    @Override
    public Integer addCategory(CategoryRequest request) {
        ShareCategory category = new ShareCategory();
        category.setName(request.getName());
        category.setAddTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        return shareCategoryMapper.insert(category);
    }

    @Override
    public ListDataEntity<ShareCategory> lists(AbstractQry qry) {
        return PageCallBackUtil.selectRtnPage(qry, this::lists);
    }
}
