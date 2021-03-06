package com.github.wkw.share.controller;

import com.github.wkw.share.annotion.AnonymousAccess;
import com.github.wkw.share.annotion.AnonymousInfoAccess;
import com.github.wkw.share.annotion.LoginUserId;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.exception.CommonException;
import com.github.wkw.share.exception.UserInfoUnFoundException;
import com.github.wkw.share.mapper.UserInfoEntityMapper;
import com.github.wkw.share.service.FollowService;
import com.github.wkw.share.service.UserInfoService;
import com.github.wkw.share.service.UserService;
import com.github.wkw.share.vo.FollowEntity;
import com.github.wkw.share.vo.ShareResponse;
import com.github.wkw.share.vo.UserEntity;
import com.github.wkw.share.vo.UserInfoEntity;
import com.github.wkw.share.vo.request.LoginRequest;
import com.github.wkw.share.vo.request.UserInfoRequest;
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

    @Autowired
    UserInfoEntityMapper userInfoEntityMapper;

    @AnonymousAccess
    @AnonymousInfoAccess
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ShareResponse<UserEntity> login(@RequestBody @Validated LoginRequest loginRequest, @RequestHeader("APP-ID") Integer appId,
                                           @RequestHeader("APP-VERSION") String appVersion,
                                           @RequestHeader("APP-MODEL") String appModel) throws CommonException {
        UserEntity entity = userService.login(loginRequest, appId, appVersion, appModel);
        return ShareResponse.ok(entity);
    }

    @AnonymousInfoAccess
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ShareResponse<UserInfoEntity> information(@LoginUserId Integer id) throws UserInfoUnFoundException {
        ShareUserInfo userInfo = userInfoService.selectByUid(id);
        UserInfoEntity entity = userInfoEntityMapper.shareUserInfoToUserInfoEntity(userInfo);
        return ShareResponse.ok(entity);
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public ShareResponse<Void> postInformation(@LoginUserId Integer id, @RequestBody @Validated UserInfoRequest request) {
        ShareUserInfo userInfo = userInfoEntityMapper.userInfoRequestToShareUserInfo(request);
        userInfo.setUserId(id);
        userInfoService.insertUserInfo(userInfo);
        return ShareResponse.ok(null);
    }


    @RequestMapping(value = "/follows", method = RequestMethod.GET)
    public ShareResponse<List<FollowEntity>> follow(@LoginUserId Integer id) throws UserInfoUnFoundException {
        return ShareResponse.ok(followService.myFollow(id));
    }

    @RequestMapping(value = "/follow/{followUserId}", method = RequestMethod.GET)
    public ShareResponse<Boolean> follow(@LoginUserId Integer id, @PathVariable("followUserId") Integer followUserId) throws UserInfoUnFoundException {
        return ShareResponse.ok(followService.follow(id, followUserId));
    }

    @RequestMapping(value = "/fans", method = RequestMethod.GET)
    public ShareResponse<List<FollowEntity>> fans(@LoginUserId Integer id) throws UserInfoUnFoundException {
        return ShareResponse.ok(followService.myFans(id));
    }


    @GetMapping(value = "/logout")
    public ShareResponse<Integer> logout(@LoginUserId Integer id) {
        return ShareResponse.ok(userService.logOut(id));
    }
}
