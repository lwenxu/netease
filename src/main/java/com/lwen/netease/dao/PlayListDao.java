package com.lwen.netease.dao;

import com.lwen.netease.entity.Music;
import com.lwen.netease.entity.PlayList;
import com.lwen.netease.repository.MusicRepository;
import com.lwen.netease.repository.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayListDao {
    @Autowired
    private PlayListRepository repository;

    public List<PlayList> findByName(String name) {
        return repository.findByName(name);
    }



}
