package com.github.wkw.share.service;

import com.github.wkw.share.domain.ShareUserInfo;

/**
 * Created by GoGo on  2018/9/5
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public interface CacheService {

    void setStringKey(String key, String value);

    void setStringKey(String key, String value, int minute);

    String getStringKey(String key);

    void removeString(String key);

    void insertUserInfo(ShareUserInfo userInfo);

    void removeUserInfo(Integer userId);

    ShareUserInfo getUserInfoByUserId(Integer userId);
}
