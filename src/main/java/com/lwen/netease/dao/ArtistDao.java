package com.lwen.netease.dao;

import com.lwen.netease.entity.Artist;
import com.lwen.netease.entity.Music;
import com.lwen.netease.repository.ArtistRepository;
import com.lwen.netease.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtistDao {
    @Autowired
    private ArtistRepository repository;

    public Artist saveArtist(Artist artist) {
        return repository.save(artist);
    }

    // public List<Music> findMusicByName(String name) {
    //     return repository.findByName(name);
    // }
    //
    // public Music findMusicById(Long id) {
    //     return repository.findById(id);
    // }

}
