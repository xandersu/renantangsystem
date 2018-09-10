package com.sufu.renantangsystem.Service.impl;

import com.sufu.renantangsystem.Service.IUserService;
import com.sufu.renantangsystem.entity.UserEntity;
import com.sufu.renantangsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        List<UserEntity> userEntityList = userRepository.select(userEntity);
        if (!CollectionUtils.isEmpty(userEntityList)) {
            return userEntityList.get(0);
        }
        return null;
    }

    @Override
    public UserEntity findById(String id) {
        return userRepository.selectByPrimaryKey(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.selectAll();
    }


}
