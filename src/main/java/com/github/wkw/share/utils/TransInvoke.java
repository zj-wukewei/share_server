package com.github.wkw.share.utils;


/**
 * Created by GoGo on  2018/9/3
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public interface TransInvoke<T, R> {
    R invoke(T o, R r);
}
