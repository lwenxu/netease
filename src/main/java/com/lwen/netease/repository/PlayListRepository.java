package com.lwen.netease.repository;

import com.lwen.netease.entity.Album;
import com.lwen.netease.entity.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayListRepository extends JpaRepository<PlayListRepository, Long> {
    List<PlayList> findByName(String name);
}
