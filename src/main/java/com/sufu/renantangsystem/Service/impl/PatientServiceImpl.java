package com.sufu.renantangsystem.Service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sufu.renantangsystem.Service.IPatientService;
import com.sufu.renantangsystem.entity.PatientEntity;
import com.sufu.renantangsystem.entity.vo.QueryPatientVO;
import com.sufu.renantangsystem.repository.PatientRepository;
import com.sufu.renantangsystem.repository.UserRepository;
import com.sufu.renantangsystem.util.GUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
@Service("patientService")
public class PatientServiceImpl implements IPatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PageInfo<PatientEntity> findAllWithPageCondition(PatientEntity patientEntity, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<PatientEntity> patientEntityList = patientRepository.select(patientEntity);

        return new PageInfo<>(patientEntityList);
    }

    public List<PatientEntity> findAll() {
        return patientRepository.selectAll();
    }

    @Override
    public List<PatientEntity> findAllByQueryPatientVO(QueryPatientVO queryPatientVO) {
        String doctorId = queryPatientVO.getDoctorId();
        Timestamp startDate = queryPatientVO.getStartDate();
        Timestamp endDate = queryPatientVO.getEndDate();

        Example example = new Example(PatientEntity.class);
        Example.Criteria criteria = example.createCriteria();
        if (startDate != null) {
            criteria.andGreaterThanOrEqualTo("modifyTime", startDate);
        }
        if (endDate != null) {
            criteria.andLessThanOrEqualTo("modifyTime", endDate);
        }
        if (StringUtils.isNotBlank(doctorId)) {
            criteria.andEqualTo("modifyUserid", doctorId);
        }
        List<PatientEntity> patientEntityList = patientRepository.selectByExample(example);
        return patientEntityList;
    }

    @Override
    public PageInfo<PatientEntity> findAllWithPageByQueryPatientVO(QueryPatientVO queryPatientVO) {
        String doctorId = queryPatientVO.getDoctorId();
        Timestamp startDate = queryPatientVO.getStartDate();
        Timestamp endDate = queryPatientVO.getEndDate();

        Example example = new Example(PatientEntity.class);
        Example.Criteria criteria = example.createCriteria();
        if (startDate != null) {
            criteria.andGreaterThanOrEqualTo("modifyTime", startDate);
        }
        if (endDate != null) {
            criteria.andLessThanOrEqualTo("modifyTime", endDate);
        }
        if (StringUtils.isNotBlank(doctorId)) {
            criteria.andEqualTo("modifyUserid", doctorId);
        }
        PageHelper.startPage(queryPatientVO.getPageNum(), queryPatientVO.getPageSize());
        List<PatientEntity> patientEntityList = patientRepository.selectByExample(example);
        return new PageInfo<>(patientEntityList);
    }

    @Transactional
    public void save(PatientEntity patientEntity) {
        patientEntity.setId(GUID.getID());
        patientRepository.insert(patientEntity);
    }

    @Override
    public PatientEntity findPatientById(String id) {
//        PatientEntity patientEntity1 = new PatientEntity();
//        patientEntity1.setId(id);
//        List<PatientEntity> patientEntityList = patientRepository.select(patientEntity1);
//        if(!CollectionUtils.isEmpty(patientEntityList)){
//            return patientEntityList.get(0);
//        }
//        return null;
        return patientRepository.selectByPrimaryKey(id);
    }
}
