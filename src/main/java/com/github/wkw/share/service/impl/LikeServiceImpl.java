package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareLikeMapper;
import com.github.wkw.share.domain.ShareLike;
import com.github.wkw.share.domain.ShareLikeExample;
import com.github.wkw.share.service.LikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by GoGo on  2018/8/16
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Service
public class LikeServiceImpl implements LikeService {
    @Resource
    ShareLikeMapper shareLikeMapper;

    @Override
    public boolean like(Integer id, Integer feedId) {
        ShareLikeExample example = new ShareLikeExample()
                .createCriteria()
                .andUserIdEqualTo(id)
                .andFeedIdEqualTo(feedId)
                .example();
        ShareLike like = shareLikeMapper.selectOneByExample(example);
        if (like == null) {
            like = new ShareLike();
            like.setAddTime(new Date());
            like.setUpdateTime(new Date());
            like.setUserId(id);
            like.setFeedId(feedId);
            shareLikeMapper.insert(like);
            return true;
        } else {
            shareLikeMapper.deleteByPrimaryKey(like.getId());
            return false;
        }
    }
}
