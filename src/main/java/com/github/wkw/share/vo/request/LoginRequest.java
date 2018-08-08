package com.github.wkw.share.vo.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Created by GoGo on  2018/8/4
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class LoginRequest {
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号码格式错误")
    @NotBlank(message = "手机号码不能为空")
    private String mobile;
    @Length(min = 6, max = 12, message = "密码为6到12位")
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
