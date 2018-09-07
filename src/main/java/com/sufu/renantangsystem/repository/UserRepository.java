package com.sufu.renantangsystem.repository;

import com.sufu.renantangsystem.entity.UserEntity;
import com.sufu.renantangsystem.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
public interface UserRepository extends MyMapper<UserEntity> {

    List<UserEntity> findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
}
