package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareUserInfoMapper;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.domain.ShareUserInfoExample;
import com.github.wkw.share.service.CacheService;
import com.github.wkw.share.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    ShareUserInfoMapper userInfoMapper;

    @Autowired
    CacheService cacheService;

    @Override
    public ShareUserInfo selectByUid(Integer uId) {
        ShareUserInfo user = cacheService.getUserInfoByUserId(uId);
        if (user != null) {
            logger.info("user info from cache");
            return user;
        }
        ShareUserInfoExample example = new ShareUserInfoExample()
                .createCriteria()
                .andUserIdEqualTo(uId)
                .example();
        ShareUserInfo info = userInfoMapper.selectOneByExample(example);
        cacheService.insertUserInfo(info);
        return info;
    }

    @Override
    public int insertUserInfo(ShareUserInfo shareUserInfo) {
        ShareUserInfo userInfo = selectByUid(shareUserInfo.getUserId());
        if (userInfo == null) {
            return userInfoMapper.insert(shareUserInfo);
        }
        cacheService.removeUserInfo(userInfo.getUserId());
        shareUserInfo.setUpdateTime(LocalDateTime.now());
        shareUserInfo.setId(userInfo.getId());
        return userInfoMapper.updateByPrimaryKeySelective(shareUserInfo);
    }

}
