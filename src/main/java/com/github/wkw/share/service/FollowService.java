package com.github.wkw.share.service;

import com.github.wkw.share.vo.UserInfoEntity;

import java.util.List;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public interface FollowService {
    List<UserInfoEntity> myFollow(Integer userId);
    List<UserInfoEntity> myFans(Integer userId);
    boolean follow(Integer userId, Integer followUserId);
}
