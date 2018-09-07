package com.sufu.renantangsystem.Service.impl;

import com.sufu.renantangsystem.Service.ICaseReportService;
import com.sufu.renantangsystem.entity.CaseReportEntity;
import com.sufu.renantangsystem.repository.CaseReportRepository;
import com.sufu.renantangsystem.util.GUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lenovo on 2018/6/18.
 */
@Service("caseReportService")
public class CaseReportServiceImpl implements ICaseReportService {

    @Autowired
    private CaseReportRepository caseReportRepository;

    @Override
    public List<CaseReportEntity> findAllByPatientIdOrderByClinicTimeDesc(String patientId) {
        return caseReportRepository.findAllByPatientIdOrderByClinicTimeDesc(patientId);
    }

    @Override
    @Transactional
    public CaseReportEntity save(CaseReportEntity caseReportEntity) {
        caseReportEntity.setId(GUID.getID());
        caseReportRepository.insert(caseReportEntity);
        return caseReportEntity;
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        caseReportRepository.deleteByPrimaryKey(id);
    }

}
