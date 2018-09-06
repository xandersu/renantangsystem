package com.sufu.renantangsystem.repository;

import com.sufu.renantangsystem.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
public interface UserRepository extends Mapper<UserEntity> {

    List<UserEntity> findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
}
