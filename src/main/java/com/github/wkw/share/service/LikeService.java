package com.github.wkw.share.service;

import com.github.wkw.share.exception.UserInfoUnFoundException;
import com.github.wkw.share.vo.LikeEntity;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public interface LikeService {
    LikeEntity like(Integer id, Integer feedId) throws UserInfoUnFoundException;

    boolean liked(Integer id, Integer feedId);
}
