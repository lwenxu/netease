package com.lwen.netease.dao;

import com.lwen.netease.entity.Album;
import com.lwen.netease.entity.Music;
import com.lwen.netease.repository.AlbumRepository;
import com.lwen.netease.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlbumDao {
    @Autowired
    private AlbumRepository repository;

    public Album saveAlbum(Album album) {
        return repository.save(album);
    }

    // public List<Album> findMusicByName(String name) {
    //     return repository.findByName(name);
    // }
    //
    // public Album findMusicById(Long id) {
    //     return repository.findById(id);
    // }

}
