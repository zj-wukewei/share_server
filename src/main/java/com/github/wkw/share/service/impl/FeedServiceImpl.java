package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareFeedMapper;
import com.github.wkw.share.domain.ShareFeed;
import com.github.wkw.share.domain.ShareFeedExample;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.service.FeedService;
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
    @Resource
    ShareFeedMapper feedMapper;

    @Autowired
    UserInfoService userInfoService;

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
                    .andTagIdEqualTo(1);
            example.setOrderByClause("add_time desc");
            return feedMapper.selectByExample(example);
        });
    }

    @Override
    public ListDataEntity<FeedEntity> feedEntityList(FeedRequest qry) {
        ListDataEntity<ShareFeed> shareFeeds = null;
        if (FeedRequest.COMMUNITY.equals(qry.getType())) {
            shareFeeds = selectCommunity(qry);
        } else if (FeedRequest.HOME.equals(qry.getType())) {
            shareFeeds = selectAll(qry);
        } else if (FeedRequest.HOT.equals(qry.getType())) {
            shareFeeds = selectHot(qry);
        }
        ListDataEntity<FeedEntity> feeds = FastjsonUtils.transformListData(shareFeeds, FeedEntity.class);
        for (FeedEntity feedEntity : feeds.getList()) {
            final ShareUserInfo info = userInfoService.selectByUid(feedEntity.getUserId());
            if (ObjectUtils.isNotNull(info)) {
                feedEntity.setUserAvatar(info.getAvatar());
                feedEntity.setUserName(info.getNickname());
            }
        }
        return feeds;
    }

}
