package com.github.wkw.share.service.impl;

import com.github.wkw.share.Constants;
import com.github.wkw.share.dao.ShareFeedMapper;
import com.github.wkw.share.domain.ShareFeed;
import com.github.wkw.share.domain.ShareFeedExample;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.exception.CommonException;
import com.github.wkw.share.exception.UserInfoUnFoundException;
import com.github.wkw.share.mapper.FeedEntityMapper;
import com.github.wkw.share.service.FeedService;
import com.github.wkw.share.service.LikeService;
import com.github.wkw.share.service.UserInfoService;
import com.github.wkw.share.thirdparty.page.AbstractQry;
import com.github.wkw.share.thirdparty.page.PageCallBackUtil;
import com.github.wkw.share.utils.ObjectUtils;
import com.github.wkw.share.vo.FeedEntity;
import com.github.wkw.share.vo.ListDataEntity;
import com.github.wkw.share.vo.request.FeedRequest;
import com.github.wkw.share.vo.request.FeedStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Service
public class FeedServiceImpl implements FeedService {


    @Resource
    ShareFeedMapper feedMapper;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    LikeService likeService;

    @Autowired
    FeedEntityMapper feedEntityMapper;

    @Override
    public ListDataEntity<ShareFeed> selectAll(FeedRequest qry) {
        return PageCallBackUtil.selectRtnPage(qry, () -> {
            ShareFeedExample example = new ShareFeedExample();
            example.setOrderByClause("add_time desc");
            if (ObjectUtils.isNotNull(qry.isDeleted())) {
                example.createCriteria()
                        .andTagIdEqualTo(Constants.FeedConstans.TAG_CONTENT)
                        .andDeletedEqualTo(qry.isDeleted());
            }

            return feedMapper.selectByExample(example);
        });
    }


    @Override
    public ListDataEntity<ShareFeed> selectHot(AbstractQry qry) {
        return PageCallBackUtil.selectRtnPage(qry, () -> {
            ShareFeedExample example = new ShareFeedExample();
            example.createCriteria()
                    .andDeletedEqualTo(false)
                    .andTagIdEqualTo(Constants.FeedConstans.TAG_CONTENT)
                    .andLikeCountGreaterThanOrEqualTo(Constants.FeedConstans.HOT_LIKE_COUNT);
            example.setOrderByClause("add_time desc");
            return feedMapper.selectByExample(example);
        });
    }

    @Override
    public ListDataEntity<ShareFeed> selectCommunity(AbstractQry qry) {
        return PageCallBackUtil.selectRtnPage(qry, () -> {
            ShareFeedExample example = new ShareFeedExample();
            example.createCriteria()
                    .andDeletedEqualTo(false)
                    .andTagIdEqualTo(Constants.FeedConstans.TAG_COMMUNITY);
            example.setOrderByClause("add_time desc");
            return feedMapper.selectByExample(example);
        });
    }

    @Override
    public ListDataEntity<FeedEntity> feedEntityList(FeedRequest qry, Integer userId) throws CommonException, UserInfoUnFoundException {
        //首页和热门针对的是内容的
        //内容和社区的区别暂定，内容只有一级评论，社区有二级评论
        ListDataEntity<ShareFeed> shareFeeds = null;
        if (FeedRequest.COMMUNITY.equals(qry.getType())) {
            shareFeeds = selectCommunity(qry);
        } else if (FeedRequest.HOME.equals(qry.getType())) {
            shareFeeds = selectAll(qry);
        } else if (FeedRequest.HOT.equals(qry.getType())) {
            shareFeeds = selectHot(qry);
        } else {
            throw new CommonException("tag 类型出错");
        }

        List<FeedEntity> feedEntities = feedEntityMapper.transformShareFeedList(shareFeeds.getList());
        ListDataEntity<FeedEntity> feeds = shareFeeds.transformBuildListEntity(feedEntities);
        for (FeedEntity feedEntity : feeds.getList()) {
            final ShareUserInfo info = userInfoService.selectByUid(feedEntity.getUserId());
            boolean liked = likeService.liked(userId, feedEntity.getId());
            if (ObjectUtils.isNotNull(info)) {
                feedEntity.setUserAvatar(info.getAvatar());
                feedEntity.setUserName(info.getNickname());
            }
            feedEntity.setLiked(liked);
        }
        return feeds;
    }

    @Override
    public ShareFeed selectById(Integer feedId) {
        return feedMapper.selectByPrimaryKey(feedId);
    }

    @Override
    public FeedEntity selectFeedEntityById(Integer feedId, Integer userId) {
        ShareFeed feed = feedMapper.selectByPrimaryKey(feedId);
        final ShareUserInfo info = userInfoService.selectByUid(feed.getUserId());
        boolean liked = likeService.liked(userId, feed.getId());
        FeedEntity entity = feedEntityMapper.shareFeedToFeedEntity(feed);
        entity.setUserName(info.getNickname());
        entity.setUserAvatar(info.getAvatar());
        entity.setLiked(liked);
        return entity;
    }

    @Override
    public int update(ShareFeed feed) {
        return feedMapper.updateByPrimaryKeySelective(feed);
    }

    @Override
    public int updateFeedStatus(FeedStatusRequest feedStatusRequest) throws CommonException {
        ShareFeed find = feedMapper.selectByPrimaryKey(feedStatusRequest.getFeedId());
        if (ObjectUtils.isNull(find)) {
            throw new CommonException("无效的feedId");
        }
        ShareFeed shareFeed = new ShareFeed();
        shareFeed.setId(feedStatusRequest.getFeedId());
        shareFeed.setUpdateTime(LocalDateTime.now());
        shareFeed.setDeleted(feedStatusRequest.isDeleted());
        return feedMapper.updateByPrimaryKeySelective(shareFeed);
    }

}
