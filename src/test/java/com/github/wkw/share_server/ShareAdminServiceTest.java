package com.github.wkw.share_server;

import com.github.wkw.share.ShareServerApplication;
import com.github.wkw.share.domain.ShareUser;
import com.github.wkw.share.service.UserService;
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
public class ShareAdminServiceTest {
    @Autowired
    private UserService userService;
    private ShareUser user;

    @Before
    public void before() throws Exception {

        user = new ShareUser();
        user.setId(1);
        user.setDeleted(false);
        user.setUpdateTime(LocalDateTime.now());
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void updateUserStatus() throws Exception {
        userService.changeUserState(1, true);
    }

}
