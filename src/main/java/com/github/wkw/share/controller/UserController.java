package com.github.wkw.share.controller;

import com.github.wkw.share.annotion.LoginUserId;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.exception.CommonException;
import com.github.wkw.share.service.FollowService;
import com.github.wkw.share.service.UserInfoService;
import com.github.wkw.share.service.UserService;
import com.github.wkw.share.utils.FastjsonUtils;
import com.github.wkw.share.vo.ShareResponse;
import com.github.wkw.share.vo.UserEntity;
import com.github.wkw.share.vo.UserInfoEntity;
import com.github.wkw.share.vo.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by GoGo on  2018/8/2
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    FollowService followService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ShareResponse<UserEntity> login(@RequestBody @Validated LoginRequest loginRequest, @RequestHeader("APP-ID") Integer appId,
                                           @RequestHeader("APP-VERSION") String appVersion,
                                           @RequestHeader("APP-MODEL") String appModel) throws CommonException {
        UserEntity entity = userService.login(loginRequest, appId, appVersion, appModel);
        return ShareResponse.ok(entity);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ShareResponse<UserInfoEntity> information(@LoginUserId Integer id) {
        ShareUserInfo userInfo = userInfoService.selectByUid(id);
        UserInfoEntity entity = FastjsonUtils.transformObject(userInfo, UserInfoEntity.class);
        return ShareResponse.ok(entity);
    }


    @RequestMapping(value = "/follows", method = RequestMethod.GET)
    public ShareResponse<List<UserInfoEntity>> follow(@LoginUserId Integer id) {
        return ShareResponse.ok(followService.myFollow(id));
    }

    @RequestMapping(value = "/follow/{followUserId}", method = RequestMethod.GET)
    public ShareResponse<Boolean> follow(@LoginUserId Integer id, @PathVariable("followUserId") Integer followUserId) {
        return ShareResponse.ok(followService.follow(id, followUserId));
    }

    @RequestMapping(value = "/fans", method = RequestMethod.GET)
    public ShareResponse<List<UserInfoEntity>> fans(@LoginUserId Integer id) {
        return ShareResponse.ok(followService.myFans(id));
    }
}
