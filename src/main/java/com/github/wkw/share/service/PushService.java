package com.github.wkw.share.service;

import com.github.wkw.share.domain.ShareFeed;
import com.github.wkw.share.exception.UserInfoUnFoundException;

/**
 * Created by GoGo on  2018/8/27
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public interface PushService {
    void pushFollow(Integer userId);

    void pushLike(Integer userId, ShareFeed feed) throws UserInfoUnFoundException;
}
