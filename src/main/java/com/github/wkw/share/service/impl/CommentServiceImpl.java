package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareCommentMapper;
import com.github.wkw.share.domain.ShareComment;
import com.github.wkw.share.domain.ShareCommentExample;
import com.github.wkw.share.domain.ShareFeed;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.mapper.CommentEntityMapper;
import com.github.wkw.share.service.CommentService;
import com.github.wkw.share.service.FeedService;
import com.github.wkw.share.service.UserInfoService;
import com.github.wkw.share.thirdparty.page.PageCallBackUtil;
import com.github.wkw.share.utils.DateUtils;
import com.github.wkw.share.utils.FastjsonUtils;
import com.github.wkw.share.utils.TransInvoke;
import com.github.wkw.share.vo.CommentEntity;
import com.github.wkw.share.vo.ListDataEntity;
import com.github.wkw.share.vo.request.CommentQryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by GoGo on  2018/9/3
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    ShareCommentMapper shareCommentMapper;

    @Autowired
    UserInfoService userService;

    @Autowired
    CommentEntityMapper commentEntityMapper;

    @Autowired
    FeedService feedService;

    @Transactional
    @Override
    public int insertComment(ShareComment shareComment) {
        ShareFeed shareFeed = feedService.selectById(shareComment.getFeedId());
        shareFeed.setUpdateTime(LocalDateTime.now());
        shareFeed.setCommentCount(shareFeed.getCommentCount() + 1);
        feedService.update(shareFeed);
        return shareCommentMapper.insertSelective(shareComment);
    }

    @Override
    public ListDataEntity<CommentEntity> commentsList(CommentQryRequest request) {
        ListDataEntity<ShareComment> comments = list(request);
        return FastjsonUtils.transformListData(comments, CommentEntity.class, (TransInvoke<ShareComment, CommentEntity>) (shareComment, commentEntity) -> {
            ShareUserInfo info = userService.selectByUid(shareComment.getFromUid());
            commentEntity.setFromNickName(info.getNickname());
            commentEntity.setAvatar(info.getAvatar());
            commentEntity.setTime(DateUtils.betweenTime(shareComment.getAddTime()));
            if (shareComment.getId() != null) {
                ShareUserInfo toUserInfo = userService.selectByUid(shareComment.getToUid());
                commentEntity.setToNickName(toUserInfo.getNickname());
                commentEntity.setChildComments(selectChildComments(request.getFeedId(), shareComment.getId()));
            }
            return commentEntity;
        });
    }

    @Override
    public ListDataEntity<ShareComment> list(CommentQryRequest request) {
        ShareCommentExample example = new ShareCommentExample()
                .createCriteria()
                .andFeedIdEqualTo(request.getFeedId())
                .andTIdIsNull()
                .example();
        return PageCallBackUtil.selectRtnPage(request, () -> shareCommentMapper.selectByExample(example));
    }

    @Override
    public List<CommentEntity> selectChildComments(Integer feedId, Integer commentId) {
        ShareCommentExample example = new ShareCommentExample()
                .createCriteria()
                .andFeedIdEqualTo(feedId)
                .andTIdEqualTo(commentId)
                .example();
        return shareCommentMapper.selectByExample(example).stream()
                .map((it) -> {
                    CommentEntity entity = commentEntityMapper.shareCommentToCommentEntity(it);
                    entity.setTime(DateUtils.betweenTime(it.getAddTime()));
                    if (it.getFromUid() != null) {
                        ShareUserInfo fromUserInfo = userService.selectByUid(it.getFromUid());
                        entity.setFromNickName(fromUserInfo.getNickname());
                        entity.setAvatar(fromUserInfo.getAvatar());

                    }
                    if (it.getToUid() != null) {
                        ShareUserInfo toUserInfo = userService.selectByUid(it.getToUid());
                        entity.setToNickName(toUserInfo.getNickname());

                    }
                    return entity;
                })
                .collect(Collectors.toList());
    }

    @Override
    public long commentFeedCount(Integer feedId) {
        ShareCommentExample example = new ShareCommentExample()
                .createCriteria()
                .andFeedIdEqualTo(feedId)
                .example();
        return shareCommentMapper.countByExample(example);
    }
}
