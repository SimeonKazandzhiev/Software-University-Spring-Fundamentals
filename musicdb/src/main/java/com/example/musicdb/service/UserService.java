package com.example.musicdb.service;

import com.example.musicdb.model.entity.User;
import com.example.musicdb.model.service.UserServiceModel;

public interface UserService {

    boolean registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUserNameAndPassword(String username, String password);

    void loginUser(UserServiceModel user);

    User findUserById(Long id);
}
