package com.github.wkw.share.service;

import com.github.wkw.share.domain.ShareFeed;
import com.github.wkw.share.thirdparty.page.AbstractQry;
import com.github.wkw.share.vo.FeedEntity;
import com.github.wkw.share.vo.ListDataEntity;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public interface FeedService {
    ListDataEntity<ShareFeed> selectAll(AbstractQry qry);
    ListDataEntity<FeedEntity> feedEntityList(AbstractQry qry);
}
