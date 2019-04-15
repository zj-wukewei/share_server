package com.github.wkw.share_server;

import com.github.wkw.share.ShareServerApplication;
import com.github.wkw.share.domain.ShareUserInfo;
import com.github.wkw.share.service.impl.UserInfoServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShareServerApplication.class)
public class ShareUserInfoServiceTest {
    @Autowired
    private UserInfoServiceImpl userInfoService;
    private ShareUserInfo user;

    @Before
    public void before() throws Exception {
        user = new ShareUserInfo();
        user.setUserId(1);
        user.setNickname("测试吴11");
        user.setBio("http://www.baidu.com");

    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void insertUserInfo() throws Exception {
        userInfoService.selectByUid(1);
    }

}
