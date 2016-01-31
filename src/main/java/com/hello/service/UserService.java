package com.hello.service;

import com.hello.model.User;

public interface UserService {

    void create(User user);

    User findUser(Integer id);
}
