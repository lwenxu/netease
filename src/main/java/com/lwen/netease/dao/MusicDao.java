package com.lwen.netease.dao;

import com.lwen.netease.entity.Music;
import com.lwen.netease.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MusicDao {
    @Autowired
    private MusicRepository repository;

    public Music saveMusic(Music music) {
        return repository.save(music);
    }

    public List<Music> findMusicByName(String name) {
        return repository.findByNameLike("%"+name+"%");
    }

    public Music findMusicById(Long id) {
        return repository.findByMId(id);
    }

}
