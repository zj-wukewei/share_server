package com.github.wkw.share.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by GoGo on  2018/8/6
 * 接口不需要基本信息完善
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousInfoAccess {

}
