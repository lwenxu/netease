package com.lwen.netease.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    private String birthday;
    private String detailDescription;
    private String backgroundUrl;
    //1 man 2 women
    private Integer gender;
    private String signature;
    private String description;
    private String nickname;
    private String avatarUrl;
    @Id
    private Long userId;

    public User(String birthday, String detailDescription, String backgroundUrl, Integer gender, String signature, String description, String nickname, String avatarUrl, Long userId) {
        this.birthday = birthday;
        this.detailDescription = detailDescription;
        this.backgroundUrl = backgroundUrl;
        this.gender = gender;
        this.signature = signature;
        this.description = description;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
        this.userId = userId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User() {
    }
}
