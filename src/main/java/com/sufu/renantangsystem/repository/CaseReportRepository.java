package com.sufu.renantangsystem.repository;

import com.sufu.renantangsystem.entity.CaseReportEntity;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
public interface CaseReportRepository extends Mapper<CaseReportEntity> {

    List<CaseReportEntity> findAllByPatientIdOrderByClinicTimeDesc(String patientId);

}
