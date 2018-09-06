package com.sufu.renantangsystem.Service;

import com.sufu.renantangsystem.entity.PatientEntity;
import com.sufu.renantangsystem.entity.QueryPatientVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
public interface IPatientService {

    Page<PatientEntity> findAllWithPageCondition(PatientEntity patientEntity, int pageNum, int pageSize);

    List<PatientEntity> findAll();

    List<PatientEntity> findAllByQueryPatientVO(QueryPatientVO queryPatientVO);

    Page<PatientEntity> findAllWithPageByQueryPatientVO(QueryPatientVO queryPatientVO);

    public void save(PatientEntity patientEntity);

    PatientEntity findPatientById(String id);

}
