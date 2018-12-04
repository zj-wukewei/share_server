package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareFeedMapper;
import com.github.wkw.share.domain.ShareFeed;
import com.github.wkw.share.domain.ShareFeedExample;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.exception.CommonException;
import com.github.wkw.share.exception.UserInfoUnFoundException;
import com.github.wkw.share.service.FeedService;
import com.github.wkw.share.service.LikeService;
import com.github.wkw.share.service.UserInfoService;
import com.github.wkw.share.thirdparty.page.AbstractQry;
import com.github.wkw.share.thirdparty.page.PageCallBackUtil;
import com.github.wkw.share.utils.FastjsonUtils;
import com.github.wkw.share.utils.ObjectUtils;
import com.github.wkw.share.vo.FeedEntity;
import com.github.wkw.share.vo.ListDataEntity;
import com.github.wkw.share.vo.request.FeedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Service
public class FeedServiceImpl implements FeedService {

    private static final int HOT_LIKE_COUNT = 100;
    //社区
    private static final int TAG_COMMUNITY = 1;
    //内容
    private static final int TAG_CONTENT = 2;
    @Resource
    ShareFeedMapper feedMapper;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    LikeService likeService;

    @Override
    public ListDataEntity<ShareFeed> selectAll(FeedRequest qry) {
        return PageCallBackUtil.selectRtnPage(qry, () -> {
            ShareFeedExample example = new ShareFeedExample();
            example.setOrderByClause("add_time desc");
            return feedMapper.selectByExample(example);
        });
    }

    @Override
    public ListDataEntity<ShareFeed> selectHot(AbstractQry qry) {
        return PageCallBackUtil.selectRtnPage(qry, () -> {
            ShareFeedExample example = new ShareFeedExample();
            example.createCriteria()
                    .andLikeCountGreaterThanOrEqualTo(HOT_LIKE_COUNT);
            example.setOrderByClause("add_time desc");
            return feedMapper.selectByExample(example);
        });
    }

    @Override
    public ListDataEntity<ShareFeed> selectCommunity(AbstractQry qry) {
        return PageCallBackUtil.selectRtnPage(qry, () -> {
            ShareFeedExample example = new ShareFeedExample();
            example.createCriteria()
                    .andTagIdEqualTo(TAG_COMMUNITY);
            example.setOrderByClause("add_time desc");
            return feedMapper.selectByExample(example);
        });
    }

    @Override
    public ListDataEntity<FeedEntity> feedEntityList(FeedRequest qry, Integer userId) throws CommonException, UserInfoUnFoundException {
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
        ListDataEntity<FeedEntity> feeds = FastjsonUtils.transformListData(shareFeeds, FeedEntity.class);
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
    public FeedEntity selectFeedEntityById(Integer feedId) {
        ShareFeed feed = feedMapper.selectByPrimaryKey(feedId);
        return FastjsonUtils.transformObject(feed, FeedEntity.class);
    }

    @Override
    public int update(ShareFeed feed) {
        return feedMapper.updateByPrimaryKeySelective(feed);
    }

}
