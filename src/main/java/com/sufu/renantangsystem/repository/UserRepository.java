package com.sufu.renantangsystem.repository;

import com.sufu.renantangsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
public interface UserRepository extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {

    List<UserEntity> findByUserNameAndPassword(String userName, String password);
}
