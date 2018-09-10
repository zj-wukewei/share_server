package com.github.wkw.share.vo.request;

import javax.validation.constraints.NotBlank;

/**
 * Created by GoGo on  2018/9/10
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class UserInfoRequest {

    @NotBlank(message = "nickname 不能为空")
    private String nickname;

    @NotBlank(message = "categoryId 不能为空")
    private Integer categoryId;

    private String avatar;


    private String bio;


    @NotBlank(message = "gender 不能为空")
    private Integer gender;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
