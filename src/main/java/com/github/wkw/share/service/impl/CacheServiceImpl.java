package com.github.wkw.share.service.impl;

import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.service.CacheService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by GoGo on  2018/9/5
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Service
public class CacheServiceImpl implements CacheService {
    private static final ConcurrentHashMap<Integer, ShareUserInfo> userInfoCache = new ConcurrentHashMap<>();

    @Override
    public void insertUserInfo(ShareUserInfo userInfo) {
        if (userInfo != null) {
            userInfoCache.put(userInfo.getUserId(), userInfo);
        }
    }

    @Override
    public ShareUserInfo getUserInfoByUserId(Integer userId) {
        if (userInfoCache.containsKey(userId)) {
            return userInfoCache.get(userId);
        }
        return null;
    }
}
