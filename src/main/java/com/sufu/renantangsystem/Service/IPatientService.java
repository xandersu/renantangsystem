package com.sufu.renantangsystem.Service;

import com.github.pagehelper.PageInfo;
import com.sufu.renantangsystem.entity.PatientEntity;
import com.sufu.renantangsystem.entity.QueryPatientVO;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
public interface IPatientService {

    PageInfo<PatientEntity> findAllWithPageCondition(PatientEntity patientEntity, int pageNum, int pageSize);

    List<PatientEntity> findAll();

    List<PatientEntity> findAllByQueryPatientVO(QueryPatientVO queryPatientVO);

    PageInfo<PatientEntity> findAllWithPageByQueryPatientVO(QueryPatientVO queryPatientVO);

    public void save(PatientEntity patientEntity);

    PatientEntity findPatientById(String id);

}
