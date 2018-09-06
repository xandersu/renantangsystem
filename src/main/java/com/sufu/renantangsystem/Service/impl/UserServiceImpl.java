package com.sufu.renantangsystem.Service.impl;

import com.sufu.renantangsystem.Service.IUserService;
import com.sufu.renantangsystem.entity.UserEntity;
import com.sufu.renantangsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserEntity login(UserEntity userEntity) {
        List<UserEntity> users = userRepository.findByUserNameAndPassword(userEntity.getUserName(), userEntity.getPassword());
        int size = users.size();
        if (size == 1) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public UserEntity findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }


}
