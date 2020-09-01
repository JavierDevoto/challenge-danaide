package com.challenge.service;

import com.challenge.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User updateUser(User user);

    List<User> getAllUsers();

    User getUserByPk(String username);

    List<User> getVipClients();

    User doUserVip(String username);

    void cleanNoVip();
}
