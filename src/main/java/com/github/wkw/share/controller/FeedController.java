package com.github.wkw.share.controller;

import com.github.wkw.share.annotion.LoginUserId;
import com.github.wkw.share.exception.CommonException;
import com.github.wkw.share.exception.UserInfoUnFoundException;
import com.github.wkw.share.service.FeedService;
import com.github.wkw.share.service.LikeService;
import com.github.wkw.share.vo.FeedEntity;
import com.github.wkw.share.vo.LikeEntity;
import com.github.wkw.share.vo.ListDataEntity;
import com.github.wkw.share.vo.ShareResponse;
import com.github.wkw.share.vo.request.FeedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by GoGo on  2018/8/2
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@RequestMapping("/feed")
@RestController
public class FeedController {

    @Autowired
    FeedService feedService;

    @Autowired
    LikeService likeService;


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ShareResponse<ListDataEntity<FeedEntity>> lists(@RequestBody @Validated FeedRequest qry, @LoginUserId Integer id) throws CommonException, UserInfoUnFoundException {
        qry.setDeleted(false);
        return ShareResponse.ok(feedService.feedEntityList(qry, id));
    }

    @RequestMapping(value = "/{feedId}", method = RequestMethod.GET)
    public ShareResponse<FeedEntity> feedDetail(@PathVariable("feedId") Integer feedId, @LoginUserId Integer id) throws CommonException {
        if (feedId == null) {
            throw new CommonException("feedId 不能为空");
        }
        return ShareResponse.ok(feedService.selectFeedEntityById(feedId, id));
    }

    @RequestMapping(value = "/like/{feedId}", method = RequestMethod.GET)
    public ShareResponse<LikeEntity> like(@LoginUserId Integer id, @PathVariable("feedId") Integer feedId) throws UserInfoUnFoundException {
        return ShareResponse.ok(likeService.like(id, feedId));
    }
}
