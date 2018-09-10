package com.sufu.renantangsystem.repository;

import com.sufu.renantangsystem.entity.CaseReportEntity;
import com.sufu.renantangsystem.util.MyMapper;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
public interface CaseReportRepository extends MyMapper<CaseReportEntity> {

    List<CaseReportEntity> findAllByPatientIdOrderByClinicTimeDesc(String patientId);

}
