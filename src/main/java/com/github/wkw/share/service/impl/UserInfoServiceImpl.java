package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareUserInfoMapper;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.domain.ShareUserInfoExample;
import com.github.wkw.share.exception.UserInfoUnFoundException;
import com.github.wkw.share.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    ShareUserInfoMapper userInfoMapper;

    @Override
    public ShareUserInfo selectByUid(Integer uId) throws UserInfoUnFoundException {
        ShareUserInfoExample example = new ShareUserInfoExample()
                .createCriteria()
                .andUserIdEqualTo(uId)
                .example();
        ShareUserInfo info = userInfoMapper.selectOneByExample(example);
        if (info == null) {
            throw new UserInfoUnFoundException();
        }
        return info;
    }
}
