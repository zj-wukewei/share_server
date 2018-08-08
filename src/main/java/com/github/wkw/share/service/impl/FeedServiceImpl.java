package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareFeedMapper;
import com.github.wkw.share.domain.ShareFeed;
import com.github.wkw.share.domain.ShareFeedExample;
import com.github.wkw.share.service.FeedService;
import com.github.wkw.share.thirdparty.page.AbstractQry;
import com.github.wkw.share.thirdparty.page.PageCallBackUtil;
import com.github.wkw.share.vo.ListDataEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Service
public class FeedServiceImpl implements FeedService {

    @Resource
    ShareFeedMapper feedMapper;

    @Override
    public ListDataEntity<ShareFeed> selectAll(AbstractQry qry) {
        return PageCallBackUtil.selectRtnPage(qry, () -> {
            ShareFeedExample example = new ShareFeedExample();
            example.setOrderByClause("add_time desc");
            return feedMapper.selectByExample(example);
        });
    }
}
