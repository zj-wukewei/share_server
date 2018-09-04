package com.github.wkw.share.thirdparty.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by GoGo on  2018/9/4
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class UserInfoFilter extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("UserInfoFilter doFilter");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
