package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareFollowMapper;
import com.github.wkw.share.domain.ShareFollow;
import com.github.wkw.share.domain.ShareFollowExample;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.exception.UserInfoUnFoundException;
import com.github.wkw.share.mapper.FollowEntityMapper;
import com.github.wkw.share.service.FollowService;
import com.github.wkw.share.service.PushService;
import com.github.wkw.share.service.UserInfoService;
import com.github.wkw.share.vo.FollowEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by GoGo on  2018/8/16
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Service
public class FollowServiceImpl implements FollowService {

    private static final int SINGLE_WAY_FOLLOW = 1;
    private static final int TWO_WAY_FOLLOW = 2;
    @Resource
    ShareFollowMapper shareFollowMapper;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    SimpMessagingTemplate smt;

    @Autowired
    PushService pushService;

    @Autowired
    FollowEntityMapper followMapper;

    @Override
    public List<FollowEntity> myFollow(Integer userId) throws UserInfoUnFoundException {
        ShareFollowExample example = new ShareFollowExample()
                .createCriteria()
                .andUserLeftEqualTo(userId)
                .example();
        List<ShareFollow> follows = shareFollowMapper.selectByExample(example);
        if (follows != null) {
            List<FollowEntity> userInfoList = new ArrayList<>(follows.size());
            for (ShareFollow follow : follows) {
                ShareUserInfo userInfo = userInfoService.selectByUid(follow.getUserRight());
                FollowEntity entity = followMapper.shareUserInfoToFollow(userInfo, true);
                userInfoList.add(entity);
            }
            return userInfoList;
        }
        return Collections.emptyList();
    }

    @Override
    public List<FollowEntity> myFans(Integer userId) throws UserInfoUnFoundException {
        ShareFollowExample example = new ShareFollowExample()
                .createCriteria()
                .andUserRightEqualTo(userId)
                .example();
        List<ShareFollow> follows = shareFollowMapper.selectByExample(example);
        if (follows != null) {
            List<FollowEntity> userInfoList = new ArrayList<>(follows.size());
            for (ShareFollow follow : follows) {
                ShareUserInfo userInfo = userInfoService.selectByUid(follow.getUserLeft());
                ShareFollowExample followExample = new ShareFollowExample()
                        .createCriteria()
                        .andUserLeftEqualTo(userId)
                        .andUserRightEqualTo(userInfo.getUserId())
                        .example();
                ShareFollow shareFollow = shareFollowMapper.selectOneByExample(followExample);
                FollowEntity entity = followMapper.shareUserInfoToFollow(userInfo, shareFollow != null);
                userInfoList.add(entity);
            }
            return userInfoList;
        }
        return Collections.emptyList();
    }

    @Transactional
    @Override
    public boolean follow(Integer userId, Integer followUserId) throws UserInfoUnFoundException {
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
            follow.setAddTime(LocalDateTime.now());
            follow.setUpdateTime(LocalDateTime.now());
            follow.setUserLeft(userId);
            follow.setUserRight(followUserId);
            follow.setRelation(myFans == null ? SINGLE_WAY_FOLLOW : TWO_WAY_FOLLOW);
            shareFollowMapper.insert(follow);
            if (myFans != null) {
                //有关注我的
                myFans.setRelation(TWO_WAY_FOLLOW);
                myFans.setUpdateTime(LocalDateTime.now());
                shareFollowMapper.updateByPrimaryKeySelective(myFans);
            }
            pushService.pushFollow(userId);
            return true;
        } else {
            //取消关注
            shareFollowMapper.deleteByPrimaryKey(myFollow.getId());
            if (myFans != null) {
                //有关注我的
                shareFollowMapper.deleteByPrimaryKey(myFollow.getId());
                myFans.setRelation(SINGLE_WAY_FOLLOW);
                myFans.setUpdateTime(LocalDateTime.now());
                shareFollowMapper.updateByPrimaryKeySelective(myFans);
            }
            return false;
        }

    }

}
