package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareFollowMapper;
import com.github.wkw.share.domain.ShareFollow;
import com.github.wkw.share.domain.ShareFollowExample;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.service.FollowService;
import com.github.wkw.share.service.UserInfoService;
import com.github.wkw.share.utils.FastjsonUtils;
import com.github.wkw.share.vo.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by GoGo on  2018/8/16
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Service
public class FollowServiceImpl implements FollowService {

    private static final int SINGLEWAY_FOLLOW = 1;
    private static final int TWO_WAY_FOLLOW = 2;
    @Resource
    ShareFollowMapper shareFollowMapper;

    @Autowired
    UserInfoService userInfoService;

    @Override
    public List<UserInfoEntity> myFollow(Integer userId) {
        ShareFollowExample example = new ShareFollowExample()
                .createCriteria()
                .andUserLeftEqualTo(userId)
                .example();
        List<ShareFollow> follows = shareFollowMapper.selectByExample(example);
        if (follows != null) {
            List<UserInfoEntity> userInfoList = new ArrayList<>(follows.size());
            for (ShareFollow follow : follows) {
                ShareUserInfo userInfo = userInfoService.selectByUid(follow.getUserRight());
                UserInfoEntity entity = FastjsonUtils.transformObject(userInfo, UserInfoEntity.class);
                userInfoList.add(entity);
            }
            return userInfoList;
        }
        return Collections.emptyList();
    }

    @Override
    public List<UserInfoEntity> myFans(Integer userId) {
        ShareFollowExample example = new ShareFollowExample()
                .createCriteria()
                .andUserRightEqualTo(userId)
                .example();
        List<ShareFollow> follows = shareFollowMapper.selectByExample(example);
        if (follows != null) {
            List<UserInfoEntity> userInfoList = new ArrayList<>(follows.size());
            for (ShareFollow follow : follows) {
                ShareUserInfo userInfo = userInfoService.selectByUid(follow.getUserLeft());
                UserInfoEntity entity = FastjsonUtils.transformObject(userInfo, UserInfoEntity.class);
                userInfoList.add(entity);
            }
            return userInfoList;
        }
        return Collections.emptyList();
    }

    @Override
    public boolean follow(Integer userId, Integer followUserId) {
        ShareFollowExample example = new ShareFollowExample()
                .createCriteria()
                .andUserLeftEqualTo(userId)
                .andUserRightEqualTo(followUserId)
                .example();
        ShareFollow myFollow = shareFollowMapper.selectOneByExample(example);

        ShareFollowExample fansExample = new ShareFollowExample()
                .createCriteria()
                .andUserLeftEqualTo(followUserId)
                .andUserRightEqualTo(userId)
                .example();
        ShareFollow myFans = shareFollowMapper.selectOneByExample(fansExample);

        if (myFollow == null) {
            //关注
            ShareFollow follow = new ShareFollow();
            follow.setAddTime(new Date());
            follow.setUpdateTime(new Date());
            follow.setUserLeft(userId);
            follow.setUserRight(followUserId);
            follow.setRelation(myFans == null ? SINGLEWAY_FOLLOW : TWO_WAY_FOLLOW);
            shareFollowMapper.insert(follow);
            if (myFans != null) {
                //有关注我的
                myFans.setRelation(TWO_WAY_FOLLOW);
                myFans.setUpdateTime(new Date());
                shareFollowMapper.updateByPrimaryKeySelective(myFans);
            }
            return true;

        } else {
            //取消关注
            shareFollowMapper.deleteByPrimaryKey(myFollow.getId());
            if (myFans != null) {
                //有关注我的
                shareFollowMapper.deleteByPrimaryKey(myFollow.getId());
                myFans.setRelation(SINGLEWAY_FOLLOW);
                myFans.setUpdateTime(new Date());
                shareFollowMapper.updateByPrimaryKeySelective(myFans);
            }
            return false;
        }
    }


}
