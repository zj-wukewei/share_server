package com.github.wkw.share.service.impl;

import com.github.wkw.share.cache.ShareUserInfoCache;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by GoGo on  2018/9/5
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Service
public class CacheServiceImpl implements CacheService {

    public static final int USER_INFO_TIMEUNIT = 60 * 24 * 3;

    @Autowired
    ShareUserInfoCache shareUserInfoCache;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void setStringKey(String key, String value) {
        ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
        ops.set(key, value);
    }

    @Override
    public void setStringKey(String key, String value, int minute) {
        ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
        ops.set(key, value, minute, TimeUnit.MINUTES);
    }

    @Override
    public String getStringKey(String key) {
        ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
        return ops.get(key);
    }

    @Override
    public void removeString(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public void insertUserInfo(ShareUserInfo userInfo) {
        if (userInfo != null) {
            shareUserInfoCache.setById(userInfo.getUserId().toString(), userInfo, USER_INFO_TIMEUNIT);
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
        return shareUserInfoCache.getById(userId.toString());
    }
}
