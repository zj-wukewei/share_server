package com.github.wkw.share.service;

import com.github.wkw.share.domain.ShareUserInfo;

/**
 * Created by GoGo on  2018/9/5
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public interface CacheService {
    void insertUserInfo(ShareUserInfo userInfo);

    ShareUserInfo getUserInfoByUserId(Integer userId);
}
