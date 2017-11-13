package com.lwen.netease.entity;


import javax.persistence.*;

@Entity
@Table
public class Album {
    @Id
    private Long aId;
    private String name;
    @OneToOne
    @JoinColumn
    private Artist artist;
    private String publishTime;
    private Integer size;

    public Album(Long aId, String name, Artist artist, String publishTime, Integer size) {
        this.aId = aId;
        this.name = name;
        this.artist = artist;
        this.publishTime = publishTime;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Album() {
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }


    @Override
    public String toString() {
        return "Album{" +
                ", aId=" + aId +
                ", name='" + name + '\'' +
                ", artist=" + artist +
                ", publishTime='" + publishTime + '\'' +
                ", size=" + size +
                '}';
    }
}
