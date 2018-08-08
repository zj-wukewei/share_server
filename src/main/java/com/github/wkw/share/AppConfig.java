package com.github.wkw.share;

import com.github.wkw.share.annotion.LoginUserIdHandlerMethodArgumentResolver;
import com.github.wkw.share.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Autowired
    UserService userService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginUserIdHandlerMethodArgumentResolver(userService));
    }
}
