package com.github.wkw.share_server;

import com.github.wkw.share.ShareServerApplication;
import com.github.wkw.share.domain.ShareUser;
import com.github.wkw.share.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShareServerApplication.class)
public class ShareUserServiceTest {
    @Autowired
    private UserServiceImpl userService;
    private ShareUser user;

    @Before
    public void before() throws Exception {
        user = new ShareUser();
        user.setPhone("18258065554");
        user.setPassword("123456");
        user.setToken("45ggghghg");
        user.setAddTime(new Date());
        user.setUpdateTime(new Date());
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void insertUser() throws Exception {
        int id = userService.insertUser(user);
        System.out.println(String.valueOf("id" + id));
        System.out.println(String.valueOf("id" + user));
    }

}
