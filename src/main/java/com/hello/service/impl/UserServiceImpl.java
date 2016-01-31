package com.hello.service.impl;

import com.hello.model.User;
import com.hello.repository.UserRepository;
import com.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public void create(User user) {
        repository.save(user);
    }

    @Override
    public User findUser(Integer id) {
        return repository.findOne(id);
    }
}
