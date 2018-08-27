package com.github.wkw.share.thirdparty.websocket;

import java.security.Principal;

/**
 * Created by GoGo on  2018/8/27
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class FastPrincipal implements Principal {
    private final String name;

    public FastPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }
}
