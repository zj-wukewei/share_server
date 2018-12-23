package com.github.wkw.share_server;

import com.github.wkw.share.ShareServerApplication;
import com.github.wkw.share.domain.ShareComment;
import com.github.wkw.share.service.impl.CommentServiceImpl;
import com.github.wkw.share.vo.CommentEntity;
import com.github.wkw.share.vo.ListDataEntity;
import com.github.wkw.share.vo.request.CommentQryRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShareServerApplication.class)
public class ShareCommentServiceTest {
    @Autowired
    private CommentServiceImpl commentService;
    private ShareComment comment;
    private CommentQryRequest request;

    @Before
    public void before() throws Exception {
        comment = new ShareComment();
        comment.setFeedId(1);
        comment.setFromUid(2);
        comment.setToUid(1);
        comment.setContent("aaaa你好啊11111");
        comment.settId(5);
        comment.setAddTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        request = new CommentQryRequest();
        request.setFeedId(1);
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void insertComment() throws Exception {
        int id = commentService.insertComment(comment);
        System.out.println(String.valueOf("id" + id));
    }

    @Test
    public void list() throws Exception {
        ListDataEntity<CommentEntity> list = commentService.commentsList(request);
        System.out.print(list.getList().size());
    }
}
