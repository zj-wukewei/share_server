package com.github.wkw.share.controller;

import com.github.wkw.share.domain.ShareCategory;
import com.github.wkw.share.service.CategoryService;
import com.github.wkw.share.vo.ShareResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by GoGo on  2018/9/3
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@RequestMapping("/category")
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ShareResponse<List<ShareCategory>> list() {
        return ShareResponse.ok(categoryService.lists());
    }
}
