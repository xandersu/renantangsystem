package com.sufu.renantangsystem.Service;

import com.sufu.renantangsystem.entity.CaseReportEntity;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
public interface ICaseReportService {

    List<CaseReportEntity> findAllByPatientIdOrderByClinicTimeDesc(String patientId);

    CaseReportEntity save(CaseReportEntity caseReportEntity);

    void deleteById(String id);
}
