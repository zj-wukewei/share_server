package com.github.wkw.share_server;

import com.github.wkw.share.ShareServerApplication;
import com.github.wkw.share.domain.ShareCategory;
import com.github.wkw.share.service.impl.CategoryServiceImpl;
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
public class ShareCategoryServiceTest {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Before
    public void before() throws Exception {


    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void selectAll() throws Exception {
        List<ShareCategory> shareCategories = categoryService.lists();
        System.out.print(shareCategories.size());
    }

}
