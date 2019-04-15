package com.github.wkw.share.service.impl;

import com.github.wkw.share.cache.ShareUserInfoCache;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by GoGo on  2018/9/5
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Service
public class CacheServiceImpl implements CacheService {

    private static final int USER_INFO_TIMEUNIT = 60 * 24 * 3;

    @Autowired
    ShareUserInfoCache shareUserInfoCache;

    @Override
    public void insertUserInfo(ShareUserInfo userInfo) {
        if (userInfo != null) {
            shareUserInfoCache.set(userInfo.getUserId().toString(), userInfo, USER_INFO_TIMEUNIT);
        }
    }

    @Override
    public void removeUserInfo(Integer userId) {
        if (userId != null) {
            shareUserInfoCache.deleteKey(userId.toString());
        }
    }

    @Override
    public ShareUserInfo getUserInfoByUserId(Integer userId) {
        if (userId == null) {
            return null;
        }
        return shareUserInfoCache.get(userId.toString());
    }
}
