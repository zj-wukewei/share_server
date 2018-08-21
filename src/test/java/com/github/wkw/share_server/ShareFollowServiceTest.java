package com.github.wkw.share_server;

import com.github.wkw.share.ShareServerApplication;
import com.github.wkw.share.service.impl.FollowServiceImpl;
import com.github.wkw.share.utils.ListUtils;
import com.github.wkw.share.vo.Follow;
import com.github.wkw.share.vo.UserInfoEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShareServerApplication.class)
public class ShareFollowServiceTest {
    @Autowired
    private FollowServiceImpl userService;


    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void followUser() throws Exception {
        userService.follow(1, 2);
//        userService.follow(2, 1);
    }


    @Test
    public void myFollow() throws Exception {
        List<Follow> entityList = userService.myFollow(1);
        if (ListUtils.isNotEmpty(entityList)) {
            for (Follow entity : entityList) {
                System.out.print(entity.getUserId());
            }
        }
    }

    @Test
    public void myFans() throws Exception {
        List<Follow> entityList = userService.myFans(1);
        if (ListUtils.isNotEmpty(entityList)) {
            for (Follow entity : entityList) {
                System.out.print(entity.getUserId());
            }
        }
    }
}
