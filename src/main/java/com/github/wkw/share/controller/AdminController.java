package com.github.wkw.share.controller;

import com.github.wkw.share.domain.ShareCategory;
import com.github.wkw.share.domain.ShareUser;
import com.github.wkw.share.service.CategoryService;
import com.github.wkw.share.service.FeedService;
import com.github.wkw.share.service.LikeService;
import com.github.wkw.share.service.UserService;
import com.github.wkw.share.thirdparty.page.AbstractQry;
import com.github.wkw.share.vo.ListDataEntity;
import com.github.wkw.share.vo.ShareResponse;
import com.github.wkw.share.vo.request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by GoGo on  2018/8/2
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@RequestMapping("/admin")
@PreAuthorize("hasRole('admin')")
@RestController
public class AdminController {

    @Autowired
    FeedService feedService;

    @Autowired
    LikeService likeService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ShareResponse<ListDataEntity<ShareUser>> users(@RequestBody AbstractQry abstractQry) {
        return ShareResponse.ok(userService.users(abstractQry));
    }



    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public ShareResponse<ListDataEntity<ShareCategory>> categorys(@RequestBody AbstractQry abstractQry) {
        return ShareResponse.ok(categoryService.lists(abstractQry));
    }


    @RequestMapping(value = "/category/add", method = RequestMethod.POST)
    public ShareResponse<Integer> addCategory(@RequestBody @Validated CategoryRequest request) {
        return ShareResponse.ok(categoryService.addCategory(request));
    }
}
