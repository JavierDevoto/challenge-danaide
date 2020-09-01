package com.challenge.service;

import com.challenge.dbobjects.UserDbMapper;
import com.challenge.dbobjects.UserDB;
import com.challenge.exception.ResourceNotFoundException;
import com.challenge.model.User;
import com.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDbMapper userDbMapper;

    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;

    @Override
    public User createUser(User user) {
        UserDB userDB = userRepository.save(userDbMapper.mappObject(user));
        return userDbMapper.mappObject(userDB);
    }

    @Override
    public List<User> getAllUsers() {
        List<UserDB> usersDB = userRepository.findAll();
        return userDbMapper.mappObjects(usersDB);
    }

    @Override
    public User getUserByPk(String username) {
        Optional<UserDB> userDB = userRepository.findById(username);
        if(userDB.isPresent()){
            return userDbMapper.mappObject(userDB.get());
        } else {
            throw new ResourceNotFoundException("El usuario: " + username + " no fue encontrado");
        }
    }

    @Override
    public List<User> getVipClients() {
        List<UserDB> usersDB = userRepository.findByVipTrue();
        return userDbMapper.mappObjects(usersDB);
    }

    @Override
    public User updateUser(User user) {
        Optional<UserDB> userDB = userRepository.findById(user.getUsername());
        if(userDB.isPresent()){
            UserDB updatedUser = userRepository.save(userDbMapper.mappObject(user));
            return userDbMapper.mappObject(updatedUser);
        } else {
            throw new ResourceNotFoundException("El usuario: " + user.getUsername() + " no fue encontrado");
        }
    }

    @Override
    public User doUserVip(String username) {
        User user = getUserByPk(username);
        user.setVip(true);
        return updateUser(user);
    }

    @Override
    public void cleanNoVip(){
        List<User> users = getVipClients();
        for (User user : users) {
            if (!shoppingCartService.checkVipStatusForUser(user.getUsername())) {
                user.setVip(false);
                updateUser(user);
            }
        }
    }

}
