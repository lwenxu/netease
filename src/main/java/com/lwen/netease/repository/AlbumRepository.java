package com.lwen.netease.repository;

import com.lwen.netease.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findByNameLike(String name);
}
