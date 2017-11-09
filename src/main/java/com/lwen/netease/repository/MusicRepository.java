package com.lwen.netease.repository;

import com.lwen.netease.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music,Long>{
    public List<Music> findByName(String name);

    public Music findById(Long id);

}
