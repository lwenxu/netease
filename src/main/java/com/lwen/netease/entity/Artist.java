package com.lwen.netease.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table
public class Artist {
    @Id
    @NotFound(action= NotFoundAction.IGNORE)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String imgUrl;

    public Artist() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


    public Long getaId() {
        return id;
    }

    public void setaId(Long aId) {
        this.id = aId;
    }

    public Artist(Long aId, String name, String imgUrl) {
        this.id = aId;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Artist{" +
                ", aId=" + id +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
