package com.github.wkw.share.service;

import com.github.wkw.share.exception.UserInfoUnFoundException;
import com.github.wkw.share.vo.Follow;
import com.github.wkw.share.vo.UserInfoEntity;

import java.util.List;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public interface FollowService {
    List<Follow> myFollow(Integer userId) throws UserInfoUnFoundException;

    List<Follow> myFans(Integer userId) throws UserInfoUnFoundException;
    boolean follow(Integer userId, Integer followUserId);
}
