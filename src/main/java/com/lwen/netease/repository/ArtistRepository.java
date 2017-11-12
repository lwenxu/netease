package com.lwen.netease.repository;

import com.lwen.netease.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findByName(String name);

    Artist findById(Long id);
}
