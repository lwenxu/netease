package com.lwen.netease.dao;

import com.lwen.netease.entity.PlayList;
import com.lwen.netease.repository.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayListDao {
    @Autowired
    private PlayListRepository repository;

    public PlayList savePlayList(PlayList list) {
        return repository.save(list);
    }

    public List<PlayList> findByName(String name) {
        return repository.findByListNameLike("%"+name+"%");
    }


}
