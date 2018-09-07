package com.sufu.renantangsystem;

import com.sufu.renantangsystem.entity.CaseReportEntity;
import com.sufu.renantangsystem.entity.UserEntity;
import com.sufu.renantangsystem.repository.CaseReportRepository;
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
	@Autowired
	private CaseReportRepository caseReportRepository;


	@Test
	public void contextLoads() {
	}

	@Test
	public void testUserSelectByUserNameAndPassword() {
		List<UserEntity> userEntityList = userRepository.findByUserNameAndPassword("1", "1");
		System.out.println(userEntityList);
	}

	@Test
	public void testSaveCaseReport() {
		CaseReportEntity caseReportEntity = new CaseReportEntity();
		caseReportEntity.setRemark("666");
		caseReportRepository.insert(caseReportEntity);
	}
}
