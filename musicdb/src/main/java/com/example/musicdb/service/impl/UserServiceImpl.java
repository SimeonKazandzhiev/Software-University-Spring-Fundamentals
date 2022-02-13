package com.example.musicdb.service.impl;

import com.example.musicdb.model.entity.User;
import com.example.musicdb.model.service.UserServiceModel;
import com.example.musicdb.repository.UserRepository;
import com.example.musicdb.security.CurrentUser;
import com.example.musicdb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }


    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        User userEntity = modelMapper.map(userServiceModel, User.class);
        try {
            userRepository.save(userEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public UserServiceModel findByUserNameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(UserServiceModel user) {
        currentUser.setId(user.getId());
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }


}
