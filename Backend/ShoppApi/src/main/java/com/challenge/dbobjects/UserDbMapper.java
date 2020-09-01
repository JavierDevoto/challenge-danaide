package com.challenge.dbobjects;

import com.challenge.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDbMapper {

    public User mappObject(UserDB userDb) {
        User user = new User();
        user.setUsername(userDb.getUsername());
        user.setFirstName(userDb.getFirstName());
        user.setLastName(userDb.getLastName());
        user.setVip(userDb.getVip());
        return user;
    }

    public UserDB mappObject(User user) {
        UserDB userDb = new UserDB();
        userDb.setUsername(user.getUsername());
        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());
        userDb.setVip(user.getVip());
        return userDb;
    }

    public List<User> mappObjects(List<UserDB> usersDB) {
        List<User> users = new ArrayList<>();
        for (UserDB userDB : usersDB) {
            users.add(mappObject(userDB));
        }
        return users;
    }

}
