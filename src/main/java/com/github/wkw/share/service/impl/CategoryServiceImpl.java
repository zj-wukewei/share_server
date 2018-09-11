package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareCategoryMapper;
import com.github.wkw.share.domain.ShareCategory;
import com.github.wkw.share.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
