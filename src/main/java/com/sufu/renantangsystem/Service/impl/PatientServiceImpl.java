package com.sufu.renantangsystem.Service.impl;

import com.github.pagehelper.PageInfo;
import com.sufu.renantangsystem.Service.IPatientService;
import com.sufu.renantangsystem.entity.PatientEntity;
import com.sufu.renantangsystem.entity.QueryPatientVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
@Service("patientService")
public class PatientServiceImpl implements IPatientService {

//    @Autowired
//    private PatientRepository patientRepository;
//    @Autowired
//    private UserRepository userRepository;

    @Override
    public PageInfo<PatientEntity> findAllWithPageCondition(PatientEntity patientEntity, int pageNum, int pageSize) {
        return null;
    }

    public List<PatientEntity> findAll() {
        return null;
    }

    @Override
    public List<PatientEntity> findAllByQueryPatientVO(QueryPatientVO queryPatientVO) {
        return null;
    }

    @Override
    public PageInfo<PatientEntity> findAllWithPageByQueryPatientVO(QueryPatientVO queryPatientVO) {

        return null;
    }

	@Transactional 
    public void save(PatientEntity patientEntity) {

    }

	@Override
	public PatientEntity findPatientById(String id) {
		return null;
	}
}
