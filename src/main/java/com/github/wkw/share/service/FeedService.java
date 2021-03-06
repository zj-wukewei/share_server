package com.github.wkw.share.service;

import com.github.wkw.share.domain.ShareFeed;
import com.github.wkw.share.exception.CommonException;
import com.github.wkw.share.exception.UserInfoUnFoundException;
import com.github.wkw.share.thirdparty.page.AbstractQry;
import com.github.wkw.share.vo.FeedEntity;
import com.github.wkw.share.vo.ListDataEntity;
import com.github.wkw.share.vo.request.FeedRequest;
import com.github.wkw.share.vo.request.FeedStatusRequest;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public interface FeedService {
    ListDataEntity<ShareFeed> selectAll(FeedRequest qry);

    ListDataEntity<ShareFeed> selectHot(AbstractQry qry);

    ListDataEntity<ShareFeed> selectCommunity(AbstractQry qry);

    ListDataEntity<FeedEntity> feedEntityList(FeedRequest qry, Integer userId) throws CommonException, UserInfoUnFoundException;

    ShareFeed selectById(Integer feedId);

    FeedEntity selectFeedEntityById(Integer feedId, Integer userId);

    int update(ShareFeed feed);

    int updateFeedStatus(FeedStatusRequest feedStatusRequest) throws CommonException;



}
