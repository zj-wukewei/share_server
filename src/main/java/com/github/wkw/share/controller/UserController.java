package com.github.wkw.share.controller;

import com.github.wkw.share.annotion.LoginUserId;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.exception.CommonException;
import com.github.wkw.share.service.UserInfoService;
import com.github.wkw.share.service.UserService;
import com.github.wkw.share.utils.FastjsonUtils;
import com.github.wkw.share.utils.ObjectUtils;
import com.github.wkw.share.vo.ShareResponse;
import com.github.wkw.share.vo.UserEntity;
import com.github.wkw.share.vo.UserInfoEntity;
import com.github.wkw.share.vo.request.LoginRequest;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ShareResponse<UserEntity> login(@RequestBody @Validated LoginRequest loginRequest, @RequestHeader("APP-ID") Integer appId,
                                           @RequestHeader("APP-VERSION") String appVersion,
                                           @RequestHeader("APP-MODEL") String appModel) throws CommonException {
        if (ObjectUtils.isNull(loginRequest) || StringUtils.isNullOrEmpty(loginRequest.getMobile())) {
            throw new CommonException("用户不存在");
        }
        UserEntity entity = userService.login(loginRequest, appId, appVersion, appModel);
        return ShareResponse.ok(entity);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ShareResponse<UserInfoEntity> information(@LoginUserId Integer id) {
        ShareUserInfo userInfo = userInfoService.selectByUid(id);
        UserInfoEntity entity = FastjsonUtils.transformObject(userInfo, UserInfoEntity.class);
        return ShareResponse.ok(entity);
    }
}
