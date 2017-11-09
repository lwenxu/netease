package com.lwen.netease.entity;

import javax.persistence.*;

@Entity
@Table
public class Music {
    @Id
    @GeneratedValue
    private Long id;
    private Long mId;
    private String name;
    @ManyToOne
    @JoinColumn
    private Artist artist;
    @ManyToOne
    @JoinColumn
    private Album album;
    private String dfsId;
    private String url;
    private String singer;
    private Long times;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDfsId() {
        return dfsId;
    }

    public void setDfsId(String dfsId) {
        this.dfsId = dfsId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Long getId() {
        return id;
    }

    public Music() {
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }
}
