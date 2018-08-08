package com.github.wkw.share.annotion;

import com.github.wkw.share.Constants;
import com.github.wkw.share.domain.ShareUser;
import com.github.wkw.share.service.UserService;
import com.github.wkw.share.thirdparty.security.TokenService;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by GoGo on  2018/8/6
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class LoginUserIdHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserService userService;

    public LoginUserIdHandlerMethodArgumentResolver(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class)&&parameter.hasParameterAnnotation(LoginUserId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest request, WebDataBinderFactory webDataBinderFactory) throws Exception {
        final String token = request.getHeader(Constants.HTTP_HEADER.TOKEN);
        final String phone = TokenService.decodeToken(token)[0];
        final ShareUser u = userService.findByPhone(phone);
        return u.getId();
    }
}
