package com.github.wkw.share.thirdparty.security;

import com.github.wkw.share.Constants;
import com.github.wkw.share.dao.UserMapper;
import com.github.wkw.share.service.impl.CacheServiceImpl;
import com.github.wkw.share.utils.ShareServerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by GoGo on  2018/9/4
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class UserInfoFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(UserInfoFilter.class);

    @Resource
    UserMapper userMapper;

    @Autowired
    CacheServiceImpl mCacheServiceImpl;


    @Autowired
    private ApplicationContext applicationContext;

    private Set<String> anonymousInfoUrls;

    public UserInfoFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(Constants.HTTP_HEADER.TOKEN);
        logger.info("UserInfoFilter" + token);
        logger.info("UserInfoFilter" + httpServletRequest.getRequestURI());
        if (anonymousInfoUrls == null) {
            //这些接口不需要完善基本信息
            anonymousInfoUrls = ShareServerUtils.getAnonymousInfoUrls(applicationContext);
        }
        final String url = httpServletRequest.getRequestURI();
        if (anonymousInfoUrls.contains(url)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            final String phone = TokenService.decodeToken(token)[0];
            final String count = mCacheServiceImpl.getStringKey(phone);
            if (count == null) {
                final int userInfo = userMapper.findUserInfo(phone);
                if (userInfo == 0) {
                    logger.info("Not found this UserInfo phone:" + phone);
                    httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
                    RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/error/1010");
                    dispatcher.forward(httpServletRequest, httpServletResponse);
                    return;
                } else {
                    mCacheServiceImpl.setStringKey(phone, String.valueOf(userInfo), CacheServiceImpl.USER_INFO_TIMEUNIT);
                }
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);

        }
    }
}
