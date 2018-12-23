package com.github.wkw.share.controller;

import com.github.wkw.share.annotion.LoginUserId;
import com.github.wkw.share.domain.ShareComment;
import com.github.wkw.share.service.CommentService;
import com.github.wkw.share.utils.FastjsonUtils;
import com.github.wkw.share.vo.CommentEntity;
import com.github.wkw.share.vo.ListDataEntity;
import com.github.wkw.share.vo.ShareResponse;
import com.github.wkw.share.vo.request.CommentQryRequest;
import com.github.wkw.share.vo.request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created by GoGo on  2018/9/3
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@RequestMapping("/comment")
@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ShareResponse<Integer> comment(@RequestBody @Validated CommentRequest qry, @LoginUserId Integer id) {
        ShareComment comment = FastjsonUtils.transformObject(qry, ShareComment.class);
        comment.setFromUid(id);
        comment.setAddTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        return ShareResponse.ok(commentService.insertComment(comment));
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public ShareResponse<ListDataEntity<CommentEntity>> list(@RequestBody @Validated CommentQryRequest commentQryRequest) {
        return ShareResponse.ok(commentService.commentsList(commentQryRequest));
    }
}
