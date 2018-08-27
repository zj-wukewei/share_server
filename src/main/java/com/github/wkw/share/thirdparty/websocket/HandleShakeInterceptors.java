package com.github.wkw.share.thirdparty.websocket;

import com.github.wkw.share.Constants;
import com.github.wkw.share.thirdparty.security.TokenService;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * Created by GoGo on  2018/8/27
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class HandleShakeInterceptors implements HandshakeInterceptor {
    public static final String PHONE = "PHONE";

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        String token = ((ServletServerHttpRequest) request).getServletRequest().getHeader(Constants.HTTP_HEADER.TOKEN);
        final String phone = TokenService.decodeToken(token)[0];
        attributes.put(PHONE, phone);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
