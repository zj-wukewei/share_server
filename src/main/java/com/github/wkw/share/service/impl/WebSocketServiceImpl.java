package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareUserMapper;
import com.github.wkw.share.domain.ShareFeed;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.exception.UserInfoUnFoundException;
import com.github.wkw.share.service.PushService;
import com.github.wkw.share.service.UserInfoService;
import com.github.wkw.share.vo.PushEntity;
import com.github.wkw.share.vo.push.FollowEvent;
import com.github.wkw.share.vo.push.LikeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

/**
 * Created by GoGo on  2018/8/27
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

@Service
public class WebSocketServiceImpl implements PushService {
    private static final int LIKE = 1;
    private static final int FOLLOW = 2;
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServiceImpl.class);

    @Autowired
    ShareUserMapper userMapper;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    SimpUserRegistry simpUserRegistry;

    @Override
    public void pushFollow(Integer userId) throws UserInfoUnFoundException {
        logger.info("pushFollow" + userId);
        FollowEvent event = new FollowEvent();
        event.setUserId(userId);
        ShareUserInfo info = userInfoService.selectByUid(userId);
        event.setNickName(info.getNickname());
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(userId), "/msg", new PushEntity(FOLLOW, event));
    }

    @Override
    public void pushLike(Integer userId, ShareFeed feed) throws UserInfoUnFoundException {
        ShareUserInfo info = userInfoService.selectByUid(userId);
        LikeEvent likeEvent = new LikeEvent();
        likeEvent.setUserId(userId);
        likeEvent.setNickName(info.getNickname());
        likeEvent.setFeedId(feed.getId());
        likeEvent.setContent(feed.getContent());
        PushEntity entity = new PushEntity(LIKE, likeEvent);
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(feed.getUserId()), "/msg", entity);
        logger.info("pushLike" + userId);
    }
}
