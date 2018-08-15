package com.github.wkw.share.controller;

import com.github.wkw.share.service.FeedService;
import com.github.wkw.share.thirdparty.page.AbstractQry;
import com.github.wkw.share.vo.FeedEntity;
import com.github.wkw.share.vo.ListDataEntity;
import com.github.wkw.share.vo.ShareResponse;
import com.github.wkw.share.vo.request.FeedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ShareResponse<ListDataEntity<FeedEntity>> lists(@RequestBody @Validated FeedRequest qry) {
        return ShareResponse.ok(feedService.feedEntityList(qry));
    }
}
