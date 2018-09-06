package com.github.wkw.share.thirdparty.security;

import com.github.wkw.share.Constants;
import com.github.wkw.share.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by GoGo on  2018/9/4
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class UserInfoFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoFilter.class);

    @Resource
    UserMapper userMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(Constants.HTTP_HEADER.TOKEN);
        logger.info("UserInfoFilter" + token);
        final String phone = TokenService.decodeToken(token)[0];
        final int userInfo = userMapper.findUserInfo(phone);
        if (userInfo == 0) {
            logger.info("Not found this UserInfo phone:" + phone);
            httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/error/1010");
            dispatcher.forward(httpServletRequest, httpServletResponse);
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}