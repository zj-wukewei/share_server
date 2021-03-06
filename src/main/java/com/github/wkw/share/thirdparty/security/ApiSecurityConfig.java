package com.github.wkw.share.thirdparty.security;

import com.github.wkw.share.Constants;
import com.github.wkw.share.dao.UserMapper;
import com.github.wkw.share.utils.ShareServerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Set<String> anonymousUrls = ShareServerUtils.getAnonymousLoginUrls(applicationContext);
        http.csrf().disable()
                .authorizeRequests()
                // 自定义匿名访问所有url放行 ： 允许 匿名和带权限以及登录用户访问
                .antMatchers(anonymousUrls.toArray(new String[0])).permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/druid/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(rememberMeAuthenticationFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(userInfoFilter(), BasicAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new RedirectEntryPoint());
        http.headers().frameOptions().disable();
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new UserDetailsServiceImpl(userMapper);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(rememberMeAuthenticationProvider());
    }

    @Bean
    public UserInfoFilter userInfoFilter() {
        return new UserInfoFilter();
    }

    @Bean
    public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception {
        return new RememberMeAuthenticationFilter(authenticationManager(), tokenBasedRememberMeService());
    }

    @Bean
    public TokenService tokenBasedRememberMeService() throws Exception {
        return new TokenService(userDetailsServiceBean());
    }

    @Bean
    RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
        return new RememberMeAuthenticationProvider(Constants.HTTP_HEADER.TOKEN);
    }
}
