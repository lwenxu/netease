package com.lwen.netease.entity;

import javax.persistence.*;

@Entity
@Table
public class Artist {
    @Id
    @GeneratedValue
    private Long id;
    private Long aId;
    @Column(nullable = false)
    private String name;
    private String imgUrl;

    public Artist() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return aId;
    }

    public void setaId(Long aId) {
        this.aId = aId;
    }

    public Artist(Long aId, String name, String imgUrl) {
        this.aId = aId;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", aId=" + aId +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
