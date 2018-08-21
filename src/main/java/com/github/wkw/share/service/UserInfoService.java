package com.github.wkw.share.service;

import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.exception.UserInfoUnFoundException;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public interface UserInfoService {
    ShareUserInfo selectByUid(Integer uId) throws UserInfoUnFoundException;
}
