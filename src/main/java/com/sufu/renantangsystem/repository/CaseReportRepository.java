package com.sufu.renantangsystem.repository;

import com.sufu.renantangsystem.entity.CaseReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
public interface CaseReportRepository extends JpaRepository<CaseReportEntity, String>, JpaSpecificationExecutor<CaseReportEntity> {

    List<CaseReportEntity> findAllByPatientIdOrderByClinicTimeDesc(String patientId);

}
