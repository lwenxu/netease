package com.lwen.netease.repository;

import com.lwen.netease.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNicknameLike(String name);

    User findByUserId(Long id);

}
