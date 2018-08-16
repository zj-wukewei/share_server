package com.github.wkw.share.controller;

import com.github.wkw.share.annotion.LoginUserId;
import com.github.wkw.share.service.FeedService;
import com.github.wkw.share.service.LikeService;
import com.github.wkw.share.thirdparty.page.AbstractQry;
import com.github.wkw.share.vo.FeedEntity;
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
    public ShareResponse<ListDataEntity<FeedEntity>> lists(@RequestBody @Validated FeedRequest qry) {
        return ShareResponse.ok(feedService.feedEntityList(qry));
    }


    @RequestMapping(value = "/like/{feedId}", method = RequestMethod.GET)
    public ShareResponse<Boolean> like(@LoginUserId Integer id, @PathVariable("feedId") Integer feedId) {
        return ShareResponse.ok(likeService.like(id, feedId));
    }
}
