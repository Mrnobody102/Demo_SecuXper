package com.example.secuxper.service;

import com.example.secuxper.data.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUser();
}
