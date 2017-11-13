package com.lwen.netease.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PlayList {
    @Id
    private Long listId ;
    private String listName ;
    private String username ;
    private String coverImgUrl ;
    private Long playCount;
    private Long trackCount;
    private Long bookCount;
    private Long userId;

    public PlayList(Long listId, String listName, String username, String coverImgUrl, Long playCount, Long trackCount, Long bookCount, Long userId) {
        this.listId = listId;
        this.listName = listName;
        this.username = username;
        this.coverImgUrl = coverImgUrl;
        this.playCount = playCount;
        this.trackCount = trackCount;
        this.bookCount = bookCount;
        this.userId = userId;
    }

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    public Long getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Long trackCount) {
        this.trackCount = trackCount;
    }

    public Long getBookCount() {
        return bookCount;
    }

    public void setBookCount(Long bookCount) {
        this.bookCount = bookCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PlayList() {
    }
}
