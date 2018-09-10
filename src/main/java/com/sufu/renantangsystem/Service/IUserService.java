package com.sufu.renantangsystem.Service;

import com.sufu.renantangsystem.entity.UserEntity;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
public interface IUserService {

    UserEntity login(UserEntity userEntity);

    UserEntity findById(String id);

    List<UserEntity> findAll();

}
