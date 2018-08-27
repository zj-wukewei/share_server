package com.github.wkw.share.service.impl;

import com.github.wkw.share.dao.ShareUserMapper;
import com.github.wkw.share.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by GoGo on  2018/8/27
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

@Service
public class WebSocketServiceImpl implements PushService {
    @Autowired
    ShareUserMapper userMapper;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void pushFollow(Integer userId) {
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(userId), "/follow", userId + "订阅成功");
    }
}
