package com.github.wkw.share.service;

import com.github.wkw.share.domain.ShareComment;
import com.github.wkw.share.vo.CommentEntity;
import com.github.wkw.share.vo.ListDataEntity;
import com.github.wkw.share.vo.request.CommentQryRequest;

import java.util.List;

/**
 * Created by GoGo on  2018/9/3
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public interface CommentService {
    int insertComment(ShareComment shareComment);

    ListDataEntity<CommentEntity> commentsList(CommentQryRequest request) throws Exception;

    ListDataEntity<ShareComment> list(CommentQryRequest request);

    List<CommentEntity> selectChildComments(Integer feedId, Integer commentId);
}
