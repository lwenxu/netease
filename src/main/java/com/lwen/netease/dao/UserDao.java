package com.lwen.netease.dao;

import com.lwen.netease.entity.User;
import com.lwen.netease.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {
    @Autowired
    private UserRepository repository;

    public List<User> findUserByName(String name) {
        return repository.findByNicknameLike("%"+name+"%");
    }

    public User findUserById(Long id) {
        return repository.findByUserId(id);
    }

    public User saveUser(User user) {
        return repository.save(user);
    }
}
