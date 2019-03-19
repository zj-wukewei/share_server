package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareLikeMapper;
import com.github.wkw.share.domain.ShareFeed;
import com.github.wkw.share.domain.ShareLike;
import com.github.wkw.share.domain.ShareLikeExample;
import com.github.wkw.share.exception.CommonException;
import com.github.wkw.share.exception.UserInfoUnFoundException;
import com.github.wkw.share.service.FeedService;
import com.github.wkw.share.service.LikeService;
import com.github.wkw.share.service.PushService;
import com.github.wkw.share.vo.LikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Created by GoGo on  2018/8/16
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Service
public class LikeServiceImpl implements LikeService {
    @Resource
    ShareLikeMapper shareLikeMapper;

    @Autowired
    FeedService feedService;

    @Autowired
    PushService pushService;

    @Override
    @Transactional
    public LikeEntity like(Integer id, Integer feedId) throws UserInfoUnFoundException, CommonException {
        ShareLikeExample example = new ShareLikeExample()
                .createCriteria()
                .andUserIdEqualTo(id)
                .andFeedIdEqualTo(feedId)
                .example();
        ShareLike like = shareLikeMapper.selectOneByExample(example);
        LikeEntity entity = new LikeEntity();
        ShareFeed feed = feedService.selectById(feedId);
        if (feed == null) {
            throw new CommonException("点赞feed不能为空");
        }
        feed.setUpdateTime(LocalDateTime.now());
        if (like == null) {
            like = new ShareLike();
            like.setAddTime(LocalDateTime.now());
            like.setUpdateTime(LocalDateTime.now());
            like.setUserId(id);
            like.setFeedId(feedId);
            shareLikeMapper.insert(like);
            feed.setLikeCount(feed.getLikeCount() + 1);
            entity.setLiked(true);
            if (!feed.getUserId().equals(id)) {
                //不是自己发布的内容点赞推送
                pushService.pushLike(id, feed);
            }
        } else {
            shareLikeMapper.deleteByPrimaryKey(like.getId());
            feed.setLikeCount(feed.getLikeCount() - 1);
            entity.setLiked(false);
        }
        feedService.update(feed);
        entity.setLickCount(feed.getLikeCount());
        return entity;
    }

    @Override
    public boolean liked(Integer id, Integer feedId) {
        ShareLikeExample example = new ShareLikeExample()
                .createCriteria()
                .andUserIdEqualTo(id)
                .andFeedIdEqualTo(feedId)
                .example();
        ShareLike like = shareLikeMapper.selectOneByExample(example);
        return like != null;
    }

    @Override
    public long feedLikeCount(int feedId) {
        ShareLikeExample example = new ShareLikeExample()
                .createCriteria()
                .andFeedIdEqualTo(feedId)
                .example();
        return shareLikeMapper.countByExample(example);
    }
}
