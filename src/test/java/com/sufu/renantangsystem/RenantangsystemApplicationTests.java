package com.sufu.renantangsystem;

import com.sufu.renantangsystem.entity.UserEntity;
import com.sufu.renantangsystem.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RenantangsystemApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testUserSelectByUserNameAndPassword() {
		List<UserEntity> userEntityList = userRepository.findByUserNameAndPassword("1", "1");
		System.out.println(userEntityList);
	}
}
